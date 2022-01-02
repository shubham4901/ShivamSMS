package com.encycode.shivamsms.database;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "stocks")
@Keep
public class Stocks implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String flavour;
    private String size;
    private int quantity;

    public Stocks(String flavour, String size, int quantity) {
        this.flavour = flavour;
        this.size = size;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getFlavour() {
        return flavour;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }
}
