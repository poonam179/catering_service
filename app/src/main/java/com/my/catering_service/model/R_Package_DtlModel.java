package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class R_Package_DtlModel {
    @SerializedName("r_PCM_DtlID")
    @Expose
    int R_PCM_DtlID;
    @SerializedName("cartID")
    @Expose
    int CartID;
    @SerializedName("itemID")
    @Expose
    int ItemID;

    public R_Package_DtlModel(int r_PCM_DtlID,int cartID, int itemID) {
        this.R_PCM_DtlID = r_PCM_DtlID;
        this.CartID = cartID;
        this.ItemID = itemID;
    }

    public int getR_PCM_DtlID() {
        return R_PCM_DtlID;
    }

    public void setR_PCM_DtlID(int r_PCM_DtlID) {
        R_PCM_DtlID = r_PCM_DtlID;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }
}
