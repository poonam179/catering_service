package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageDtlModel {
    @SerializedName("seqNo")
    @Expose
    int seqNo;
    @SerializedName("pcM_DtlId")
    @Expose
    int pcM_DtlId;
    @SerializedName("spcM_Id")
    @Expose
    int spcM_Id;
    @SerializedName("cat_Id")
    @Expose
    int cat_Id;
    @SerializedName("qty")
    @Expose
    int qty;
    @SerializedName("cat_Name")
    @Expose
    String cat_Name;
    @SerializedName("itemMasterList")
    @Expose
    List<ItemMasterModel> itemMasterList;

    public PackageDtlModel(int seqNo, int pcM_DtlId, int spcM_Id, int cat_Id, int qty, String cat_Name, List<ItemMasterModel> itemMasterList) {
        this.seqNo = seqNo;
        this.pcM_DtlId = pcM_DtlId;
        this.spcM_Id = spcM_Id;
        this.cat_Id = cat_Id;
        this.qty = qty;
        this.cat_Name = cat_Name;
        this.itemMasterList = itemMasterList;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public int getPcM_DtlId() {
        return pcM_DtlId;
    }

    public void setPcM_DtlId(int pcM_DtlId) {
        this.pcM_DtlId = pcM_DtlId;
    }

    public int getSpcM_Id() {
        return spcM_Id;
    }

    public void setSpcM_Id(int spcM_Id) {
        this.spcM_Id = spcM_Id;
    }

    public int getCat_Id() {
        return cat_Id;
    }

    public void setCat_Id(int cat_Id) {
        this.cat_Id = cat_Id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCat_Name() {
        return cat_Name;
    }

    public void setCat_Name(String cat_Name) {
        this.cat_Name = cat_Name;
    }

    public List<ItemMasterModel> getItemMasterList() {
        return itemMasterList;
    }

    public void setItemMasterList(List<ItemMasterModel> itemMasterList) {
        this.itemMasterList = itemMasterList;
    }
}
