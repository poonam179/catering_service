package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckUserModel {
    @SerializedName("userId")
    @Expose
    String userId;
    @SerializedName("mobileNo")
    @Expose
    String mobileNo;

    public CheckUserModel(String userId, String mobileNo) {
        this.userId = userId;
        this.mobileNo = mobileNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
