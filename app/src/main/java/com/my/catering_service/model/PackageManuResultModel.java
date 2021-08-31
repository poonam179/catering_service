package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageManuResultModel {

    @SerializedName("data")
    @Expose
    PackageManuModel data;
    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;

    public PackageManuResultModel(PackageManuModel data, int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public PackageManuModel getData() {
        return data;
    }

    public void setData(PackageManuModel data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
