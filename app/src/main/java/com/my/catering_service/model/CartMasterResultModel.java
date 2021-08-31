package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartMasterResultModel {
    @SerializedName("data")
    @Expose
    CartMasterModel data;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public CartMasterResultModel(CartMasterModel data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public CartMasterModel getData() {
        return data;
    }

    public void setData(CartMasterModel data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
