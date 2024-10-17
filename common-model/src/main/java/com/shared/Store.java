package com.shared;

import java.io.Serializable;

public class Store implements Serializable {

    private static final long serialVersionUID = 1069270118228032176L;

    private int itemId;
    private String itemName;
    private int itemQuantity;

    public Store() {};

    public Store(int itemId, String itemName, int itemQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public int getItemId() {
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

}