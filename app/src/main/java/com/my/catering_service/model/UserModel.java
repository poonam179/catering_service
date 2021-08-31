package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("userId")
    @Expose
    String userId;
    @SerializedName("fullName")
    @Expose
    String fullName;
    @SerializedName("mobileNo")
    @Expose
    String mobileNo;
    @SerializedName("emailId")
    @Expose
    String emailId;




    public UserModel() {
    }



    public UserModel(String userId, String fullName, String mobileNo, String emailId) {
        this.userId = userId;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
