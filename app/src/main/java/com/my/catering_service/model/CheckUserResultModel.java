package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckUserResultModel {
    @SerializedName("data")
    @Expose
    CheckUserModel data;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public CheckUserResultModel(CheckUserModel data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public CheckUserModel getData() {
        return data;
    }

    public void setData(CheckUserModel data) {
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
