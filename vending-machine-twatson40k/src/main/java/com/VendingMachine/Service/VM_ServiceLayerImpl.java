package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_AuditDAO;
import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.CoinMath;
import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VM_ServiceLayerImpl implements VM_ServiceLayer {
    private final VM_DAO dao;
    private final VM_AuditDAO auditDao;
    private BigDecimal sessionBalance;

    public BigDecimal getSessionBalance() {
        return sessionBalance;
    }

    public BigDecimal setSessionBalance(BigDecimal sessionBalance) {
        this.sessionBalance = this.sessionBalance.add(sessionBalance);
        return this.sessionBalance;
    }

    public VM_ServiceLayerImpl(VM_DAO dao, VM_AuditDAO auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
        sessionBalance = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void resupplyItem(String itemId, int noOfItems) throws
            VM_PersistenceException, VM_NoItemInventoryException {
        Item item = dao.getItem(itemId);
        item.setNoOfItem(item.getNoOfItem() + noOfItems);
        dao.updateItemAmount(item);
        auditDao.writeAuditEntry("RESTOCKED: " + LocalDate.now() +
                " Item " + item.getItemId() + " Has Been Restock");

        if (dao.buyItem(item.getItemId()) == null) {
            throw new VM_NoItemInventoryException(
                    "ERROR: Could not restock " + item.getItemId());
            }
        }

    @Override
    public List<Item> getAllItems() throws VM_PersistenceException {
        return dao.getAllItems().stream()
                .filter((item) -> item.getNoOfItem() > 0)
                .sorted(Comparator.comparing(Item::getItemId))
                .collect(Collectors.toList());
    }

    @Override
    public String buyItem(String itemId) throws VM_PersistenceException,
            VM_InsufficientFundsException, VM_NoItemInventoryException {
        Item item = dao.buyItem(itemId);
        String change;
        if (item == null) {
            throw new VM_NoItemInventoryException("ERROR: Item " + itemId + " not found");
        }
        if (item.getNoOfItem() == 0) {
            auditDao.writeAuditEntry("NO STOCK: " + LocalDate.now() + " " + item.getItemId() + " "
                    + item.getItemName() + " Stock Available " + item.getNoOfItem());
            throw new VM_NoItemInventoryException("No Stock in " + itemId
                    + " Please resupply item");
        }
        if (item.getPriceTag().compareTo(sessionBalance) > 0) {
            throw new VM_InsufficientFundsException("Insufficient funds for item " + itemId
                    + " Please insert £" + (item.getPriceTag().subtract(sessionBalance)));

        } else {
            this.UpdateItemAmount(item);
            //if audi
            if (item.getNoOfItem() <= 2) {
                auditDao.writeAuditEntry("LOW STOCK: " + LocalDate.now() + " " + item.getItemId() + " "
                        + item.getItemName() + " Stock Available " + item.getNoOfItem());
            }

            //return change as string to be displayed on screen and update session balance
            change = CoinMath.getChange(sessionBalance, item.getPriceTag());
            sessionBalance = sessionBalance.subtract(item.getPriceTag());


            auditDao.writeAuditEntry("SOLD: " + LocalDate.now() + " " + item.getItemId() + " "
                    + item.getItemName() + " " + item.getPriceTag());
        }
        return change;
    }

    @Override
    public void UpdateItemAmount(Item item) throws VM_PersistenceException {
        item.setNoOfItem(item.getNoOfItem() - 1);
        dao.updateItemAmount(item);

    }

    @Override
    public void dataValidation(String itemId) throws VM_NoItemInventoryException, VM_PersistenceException {
        Item item = dao.getItem(itemId);
        if (item == null) {
            throw new VM_NoItemInventoryException("ERROR: Item " + itemId + " not found");
        }

    }
}