package com.my.catering_service.api.clients;

import android.content.Context;

import com.my.catering_service.api.LoggingInterceptor;
import com.my.catering_service.api.RestService;
import com.my.catering_service.api.ToStringConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.squareup.picasso.BuildConfig.api.LoggingInterceptor;
//import com.quintus.labs.grocerystore.api.RestService;
//import com.quintus.labs.grocerystore.api.ToStringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    //public static final String BASE_URL = "https://megagrocerystore.000webhostapp.com/";//http://<ipaddress>/directoryname
    //public static final String BASE_URL = "http://192.168.1.105:86";
    public static final String BASE_URL = "http://192.168.0.10:86";
    public static Retrofit RETROFIT = null;
    public static Retrofit RETROFIT1 = null;
    public static RestService restService = null;

    public static Retrofit getClient() {
        if (RETROFIT == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return RETROFIT;
    }

    public static Retrofit getStringClient() {
        if (RETROFIT1 == null) {
            RETROFIT1 = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(new ToStringConverterFactory())
                    .build();
        }
        return RETROFIT1;
    }

    public static RestService getRestService(final Context context) {
        if (restService == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            LoggingInterceptor loggingInterceptor = new LoggingInterceptor();

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(httpLoggingInterceptor);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            restService = retrofit.create(RestService.class);
        }
        return restService;
    }
}
