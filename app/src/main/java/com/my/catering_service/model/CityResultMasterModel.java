package com.my.catering_service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResultMasterModel {
    @SerializedName("cityList")
    @Expose
    List<CityMasterModel> cityList;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("message")
    @Expose
    String message;

    public CityResultMasterModel(List<CityMasterModel> cityList, String status, String message) {
        this.cityList = cityList;
        this.status = status;
        this.message = message;
    }

    public List<CityMasterModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityMasterModel> cityList) {
        this.cityList = cityList;
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
