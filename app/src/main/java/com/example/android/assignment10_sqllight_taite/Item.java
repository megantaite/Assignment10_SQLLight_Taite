package com.example.android.assignment10_sqllight_taite;

import java.util.UUID;

/**
 * Created by Android on 11/7/2017.
 */

public class Item {

    //primary key in the database
    private UUID id;
    private String itemName;
    private float wholesalePrice;
    private float retailPrice;
    private float salePrice;
    private int stockCount;

    public Item()
    {
        id = UUID.randomUUID();
    }

    public Item(UUID id)
    {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(float wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(float retailPrice) {
        this.retailPrice = retailPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }
}
