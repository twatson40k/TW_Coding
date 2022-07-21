package com.VendingMachine.UI;

import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.util.*;

public class VM_View {

    private final UserIO io;

    public VM_View(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Items");
        io.print("2. Insert Money");
        io.print("3. Purchase Item");
        io.print("4. Display Balance");
        io.print("5. Restock Item");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayRestockItemBanner() {
        io.print("=== Restock Item ===");
    }

    public void displayRestockSuccessBanner() {
        io.readString("Item successfully restocked.  Please hit enter to continue");
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String studentInfo = String.format("%s | %s | £ %s | %s",
                    currentItem.getItemId(),
                    currentItem.getItemName(),
                    currentItem.getPriceTag(),
                    currentItem.getNoOfItem());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public String getItemIdChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public void displayItem(String item) {
        System.out.println(item);
        io.readString("Please hit enter to continue");
    }

    public String getMoney() {
        return io.readString("=== Insert Money amount ===");
    }

    public void displayMoneySuccess(BigDecimal coinBalance) {
        if (coinBalance != null) {
            io.print("=== Current Balance £" + coinBalance + "  ===");
        } else {
            io.print("Unsuccessfully.");
        }
        io.readString("Please hit enter to continue.");
    }
    //io.print("=== Money successfully Added.  Please hit enter to continue===");}


    public void displayChange(BigDecimal sessionBalance) {
        io.readString("Your current change is £" + sessionBalance + " Please hit enter to continue.");
    }

    public void displayExitBanner(BigDecimal sessionBalance) {
        io.print("Your overall change is " + sessionBalance);
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.readString("Please hit enter to continue");
    }

    public int updateNoOfItems() {
        return io.readInt("How many do you want to add");
    }
}