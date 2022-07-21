package com.VendingMachine.Controller;

import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;
import com.VendingMachine.Service.VM_InsufficientFundsException;
import com.VendingMachine.Service.VM_NoItemInventoryException;
import com.VendingMachine.Service.VM_ServiceLayer;
import com.VendingMachine.UI.VM_View;

import java.math.BigDecimal;
import java.util.List;


public class VM_Controller {
    private final VM_View view;
    private final VM_ServiceLayer service;

    public VM_Controller(VM_ServiceLayer service, VM_View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            listItems();
            while (keepGoing) {


                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listItems();
                        break;
                    case 2:
                        insertCoin();
                        break;
                    case 3:
                        purchaseItem();
                        break;
                    case 4:
                        displayBalance();
                        break;
                    case 5:
                        restockItem();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VM_PersistenceException | VM_InsufficientFundsException | VM_NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void restockItem() throws VM_PersistenceException {
        view.displayRestockItemBanner();
        //boolean hasErrors;
        //do {
            try {
                String itemId = view.getItemIdChoice();
                service.dataValidation(itemId);
                int newNoOfItems = view.updateNoOfItems();
                service.resupplyItem(itemId, newNoOfItems);
                view.displayRestockSuccessBanner();
                //hasErrors = false;
            } catch (VM_NoItemInventoryException e) {
               // hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
       // } while (hasErrors);
    }

    private void listItems() throws VM_PersistenceException {
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }

   private void purchaseItem() throws VM_PersistenceException, VM_InsufficientFundsException, VM_NoItemInventoryException {
       boolean hasErrors;
       try {
           do {
               try {
                   String itemId = view.getItemIdChoice();
                   String item = service.buyItem(itemId);
                   view.displayItem(item);
                   hasErrors = false;
               } catch (VM_NoItemInventoryException e) {
                   hasErrors = true;
                   view.displayErrorMessage(e.getMessage());
               }
           } while (hasErrors);
       } catch (VM_InsufficientFundsException e) {
           view.displayErrorMessage(e.getMessage());
       }
   }

    private void displayBalance() throws VM_PersistenceException {
        view.displayChange(service.getSessionBalance());
    }

    //private void restockItem(){}

    private void insertCoin() throws VM_PersistenceException {
        String money = view.getMoney();
        try {
            BigDecimal coinBalance = service.setSessionBalance(new BigDecimal(money));
            view.displayMoneySuccess(coinBalance);
        } catch (Exception e) {
            view.displayErrorMessage("Invalid Input, please try again.");
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner(service.getSessionBalance());
    }

}