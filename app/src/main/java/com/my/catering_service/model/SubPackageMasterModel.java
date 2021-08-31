package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubPackageMasterModel {
    @SerializedName("spcmid")
    @Expose
    int spcmid;

    @SerializedName("spcmName")
    @Expose
    String spcmName;

    @SerializedName("spcmPrice")
    @Expose
    float spcmPrice;

    @SerializedName("miniMember")
    @Expose
    int miniMember;

    @SerializedName("dM_ID")
    @Expose
    int dM_ID;

    @SerializedName("pcmid")
    @Expose
    int pcmid;

    public SubPackageMasterModel(int spcmid, String spcmName, float spcmPrice, int miniMember, int dM_ID, int pcmid) {
        this.spcmid = spcmid;
        this.spcmName = spcmName;
        this.spcmPrice = spcmPrice;
        this.miniMember = miniMember;
        this.dM_ID = dM_ID;
        this.pcmid = pcmid;
    }

    public int getSpcmid() {
        return spcmid;
    }

    public void setSpcmid(int spcmid) {
        this.spcmid = spcmid;
    }

    public String getSpcmName() {
        return spcmName;
    }

    public void setSpcmName(String spcmName) {
        this.spcmName = spcmName;
    }

    public float getSpcmPrice() {
        return spcmPrice;
    }

    public void setSpcmPrice(float spcmPrice) {
        this.spcmPrice = spcmPrice;
    }

    public int getMiniMember() {
        return miniMember;
    }

    public void setMiniMember(int miniMember) {
        this.miniMember = miniMember;
    }

    public int getdM_ID() {
        return dM_ID;
    }

    public void setdM_ID(int dM_ID) {
        this.dM_ID = dM_ID;
    }

    public int getPcmid() {
        return pcmid;
    }

    public void setPcmid(int pcmid) {
        this.pcmid = pcmid;
    }
}
