package com.VendingMachine.DTO;

import java.math.BigDecimal;

public class Item {
    private final String itemId;
    private String itemName;
    private BigDecimal priceTag;
    private int noOfItem;

    public Item(String itemId) {
        this.itemId = itemId;
    }
	
	public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(BigDecimal priceTag) {
        this.priceTag = priceTag;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

}