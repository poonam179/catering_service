package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageManuModel {
    @SerializedName("spcmid")
    @Expose
    int spcmid;
    @SerializedName("spcmName")
    @Expose
    String spcmName;
    @SerializedName("spcmPrice")
    @Expose
    int spcmPrice;
    @SerializedName("miniMember")
    @Expose
    int miniMember;
    @SerializedName("dM_ID")
    @Expose
    int dM_ID;
    @SerializedName("pcmid")
    @Expose
    int pcmid;
    @SerializedName("package_DtlList")
    @Expose
    List<PackageDtlModel> package_DtlList;

    public PackageManuModel(int spcmid, String spcmName, int spcmPrice, int miniMember, int dM_ID, int pcmid, List<PackageDtlModel> package_DtlList) {
        this.spcmid = spcmid;
        this.spcmName = spcmName;
        this.spcmPrice = spcmPrice;
        this.miniMember = miniMember;
        this.dM_ID = dM_ID;
        this.pcmid = pcmid;
        this.package_DtlList = package_DtlList;
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

    public int getSpcmPrice() {
        return spcmPrice;
    }

    public void setSpcmPrice(int spcmPrice) {
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

    public List<PackageDtlModel> getPackage_DtlList() {
        return package_DtlList;
    }

    public void setPackage_DtlList(List<PackageDtlModel> package_DtlList) {
        this.package_DtlList = package_DtlList;
    }


}
