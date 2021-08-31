package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationModel {
    @SerializedName("UserId")
    @Expose
    int UserId;
    @SerializedName("FirstName")
    @Expose
    String FirstName;
    @SerializedName("LastName")
    @Expose
    String LastName;
    @SerializedName("EmailId")
    @Expose
    String EmailId;
    @SerializedName("MobileNo")
    @Expose
    String MobileNo;
    @SerializedName("Password")
    @Expose
    String Password;

    public RegistrationModel()
    {}
    public RegistrationModel(int userId, String firstName, String lastName, String emailId, String mobileNo, String password) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        EmailId = emailId;
        MobileNo = mobileNo;
        Password = password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
