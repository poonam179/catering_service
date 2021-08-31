package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResultModel {
    @SerializedName("userData")
    @Expose
    UserModel userData;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public UserResultModel(UserModel userData, String status, String message) {
        this.userData = userData;
        this.status = status;
        this.message = message;
    }

    public UserModel getUserModel() {
        return userData;
    }

    public void setUserModel(UserModel userData) {
        this.userData = userData;
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
