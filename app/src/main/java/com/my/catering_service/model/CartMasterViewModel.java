package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartMasterViewModel {
    @SerializedName("cartID")
    @Expose
    int CartId;
    @SerializedName("cDate")
    @Expose
    String C_Date;
    @SerializedName("cTime")
    @Expose
    String C_Time;
    @SerializedName("userID")
    @Expose
    int UserID;
    @SerializedName("noOfMember")
    @Expose
    int NoOfMember;
    @SerializedName("spcmid")
    @Expose
    int SPCMID;
    @SerializedName("packagePrice")
    @Expose
    double PackagePrice;
    @SerializedName("pcmid")
    @Expose
    int PCMID;
    @SerializedName("spcmName")
    @Expose
    String spcmName;

    public CartMasterViewModel(int cartId, String c_Date, String c_Time, int userID, int noOfMember, int SPCMID, double packagePrice, int PCMID, String spcmName) {
        CartId = cartId;
        C_Date = c_Date;
        C_Time = c_Time;
        UserID = userID;
        NoOfMember = noOfMember;
        this.SPCMID = SPCMID;
        PackagePrice = packagePrice;
        this.PCMID = PCMID;
        this.spcmName = spcmName;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public String getC_Date() {
        return C_Date;
    }

    public void setC_Date(String c_Date) {
        C_Date = c_Date;
    }

    public String getC_Time() {
        return C_Time;
    }

    public void setC_Time(String c_Time) {
        C_Time = c_Time;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getNoOfMember() {
        return NoOfMember;
    }

    public void setNoOfMember(int noOfMember) {
        NoOfMember = noOfMember;
    }

    public int getSPCMID() {
        return SPCMID;
    }

    public void setSPCMID(int SPCMID) {
        this.SPCMID = SPCMID;
    }

    public double getPackagePrice() {
        return PackagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        PackagePrice = packagePrice;
    }

    public int getPCMID() {
        return PCMID;
    }

    public void setPCMID(int PCMID) {
        this.PCMID = PCMID;
    }

    public String getSpcmName() {
        return spcmName;
    }

    public void setSpcmName(String spcmName) {
        this.spcmName = spcmName;
    }
}
