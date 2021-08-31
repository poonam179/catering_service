package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartMasterViewResultModel {

    @SerializedName("modelsData")
    @Expose
    List<CartMasterViewModel> data;
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public CartMasterViewResultModel(List<CartMasterViewModel> data, String status, String message) {

        this.data = data;
        this.status = status;
        this.message = message;
    }

    public List<CartMasterViewModel> getData() {
        return data;
    }

    public void setData(List<CartMasterViewModel> data) {
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
