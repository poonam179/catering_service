package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageMasterModel {

    @SerializedName("pcmid")
    @Expose
    int pcmid;

    @SerializedName("pcmName")
    @Expose
    String pcmName;

    @SerializedName("subPackageList")
    @Expose
    List<SubPackageMasterModel> subPackageList;


    public PackageMasterModel(int pcmid, String pcmName, List<SubPackageMasterModel> subPackageList) {
        this.pcmid = pcmid;
        this.pcmName = pcmName;
        this.subPackageList = subPackageList;
    }

    public int getPcmid() {
        return pcmid;
    }

    public void setPcmid(int pcmid) {
        this.pcmid = pcmid;
    }

    public String getPcmName() {
        return pcmName;
    }

    public void setPcmName(String pcmName) {
        this.pcmName = pcmName;
    }

    public List<SubPackageMasterModel> getSubPackageList() {
        return subPackageList;
    }

    public void setSubPackageList(List<SubPackageMasterModel> subPackageList) {
        this.subPackageList = subPackageList;
    }
}
