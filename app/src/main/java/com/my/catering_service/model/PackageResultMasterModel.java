package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageResultMasterModel {

    @SerializedName("data")
    @Expose
    List<PackageMasterModel> data;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public PackageResultMasterModel(List<PackageMasterModel> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public List<PackageMasterModel> getData() {
        return data;
    }

    public void setData(List<PackageMasterModel> data) {
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
