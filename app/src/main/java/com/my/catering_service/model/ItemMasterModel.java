package com.my.catering_service.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemMasterModel implements Serializable {
    @SerializedName("itemID")
    @Expose
    int itemID;
    @SerializedName("itemName")
    @Expose
    String itemName;
    @SerializedName("qty")
    @Expose
    int Qty;

    boolean flag;

    public ItemMasterModel(int itemID, String itemName,int qty,boolean flag) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.Qty = qty;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
