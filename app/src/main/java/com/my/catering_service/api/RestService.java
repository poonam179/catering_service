package com.my.catering_service.api;

import com.my.catering_service.model.CartMasterModel;
import com.my.catering_service.model.CartMasterResultModel;
import com.my.catering_service.model.CartMasterViewResultModel;
import com.my.catering_service.model.CheckUserResultModel;
import com.my.catering_service.model.CityResultMasterModel;
import com.my.catering_service.model.PackageManuModel;
import com.my.catering_service.model.PackageManuResultModel;
import com.my.catering_service.model.PackageResultMasterModel;
import com.my.catering_service.model.RegistrationModel;
import com.my.catering_service.model.UserResultModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RestService {


    @GET("/api/login")
    Call<UserResultModel> login(@QueryMap Map<String,Object> map);

    @GET("/api/registration")
    Call<CheckUserResultModel> registration(@QueryMap Map<String,Object> map);

    @POST("/api/registration")
    Call<RegistrationModel> createUser(@Body RegistrationModel ModelData);

    @GET("/api/CityMaster")
    Call<CityResultMasterModel> CityMasterList();

    @GET("/api/PackageMaster")
    Call<PackageResultMasterModel> PackageMaster(@QueryMap Map<String,Object> map);

    @GET("/api/SubPackage")
    Call<PackageManuResultModel> PackageManu(@QueryMap Map<String,Object> map);

    @GET("/api/CartMaster")
    Call<CartMasterViewResultModel> GetCartView(@QueryMap Map<String,Object> map);

    @POST("/api/CartMaster")
    Call<CartMasterModel> CartMaster(@Body CartMasterModel ModelsData);
}
