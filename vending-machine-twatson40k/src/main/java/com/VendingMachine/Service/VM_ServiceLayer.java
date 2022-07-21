package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VM_ServiceLayer {

    void resupplyItem(String itemId, int noOfItems) throws
            VM_PersistenceException, VM_NoItemInventoryException;

    List<Item> getAllItems() throws VM_PersistenceException;

    String buyItem(String itemId) throws VM_PersistenceException, VM_InsufficientFundsException, VM_NoItemInventoryException;

    void UpdateItemAmount(Item item) throws VM_PersistenceException;

    BigDecimal setSessionBalance(BigDecimal bigDecimal);

    BigDecimal getSessionBalance();

    void dataValidation(String itemId) throws VM_NoItemInventoryException, VM_PersistenceException;
}
