package com.my.catering_service.util.LocalStorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LocalStorage {
    public static final String KEY_USER = "User";
    private static final String USER_ID = "UserId";
    private static final String USER_NAME = "UserName";
    private static final String USER_PASSWORD = "Password";
    public static final String KEY_USER_ADDRESS = "user_address";
    public static final String CITY_ID = "CityId";
    public static final String CITY_NAME = "CityName";
    public static final String TOTAL_QTY = "TotalQty";


    private static final String IS_USER_LOGIN = "IsUserLoggedIn";



    private static LocalStorage instance = null;

    SharedPreferences sharedPreferences;
    Editor editor,userId_editor,password_editor,userName_editor,cityId_editor,cityName_editor,totalQty_editor;

    public LocalStorage(){

    }
    public LocalStorage(Context context) {
    sharedPreferences = context.getSharedPreferences("Preferences",0);
    }



    public static LocalStorage getInstance(Context context) {
        if (instance == null) {
            synchronized (LocalStorage.class) {
                if (instance == null) {
                    instance = new LocalStorage(context);
                }
            }
        }
        return instance;
    }


    public void createUserLoginSession(String user) {
        editor = sharedPreferences.edit();
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USER, user);
        editor.commit();
    }
    public String getUserLogin() {
        return sharedPreferences.getString(KEY_USER, "");
    }

    public boolean checkLogin() {
        // Check login status
        return !this.isUserLoggedIn();
    }
    public void logoutUser() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

    // ----   User Password Session
    public void createUserPasswordSession(String Password)
    {
        password_editor = sharedPreferences.edit();
        //editor.putBoolean(KEY_USER_PASSWORD,true);
        password_editor.putString(USER_PASSWORD,Password);
        password_editor.commit();
        password_editor.apply();
    }
    public String getUserPassword(){return sharedPreferences.getString(USER_PASSWORD,""); }
    public void clear_UserPassword(){
        password_editor = sharedPreferences.edit();
        password_editor.clear();
        password_editor.commit();
    }

    // ----   User Id Session
    public void createUserIdSession(String UserId)
    {
        userId_editor = sharedPreferences.edit();
        userId_editor.putString(USER_ID,UserId);
        userId_editor.commit();
        userId_editor.apply();
    }
    public String getUserId(){return sharedPreferences.getString(USER_ID,""); }

    public void clear_UserId(){
        userId_editor = sharedPreferences.edit();
        userId_editor.clear();
        userId_editor.commit();
    }

    // ----   User Id Session
    public void createUserNameSession(String UserName)
    {
        userName_editor = sharedPreferences.edit();
        userName_editor.putString(USER_NAME,UserName);
        userName_editor.commit();
        userName_editor.apply();
    }
    public String getUserName(){return sharedPreferences.getString(USER_NAME,""); }

    public void clear_UserName(){
        userName_editor = sharedPreferences.edit();
        userName_editor.clear();
        userName_editor.commit();
    }

    // ---- CityID Session
    public void createCityIDSession(int CityId)
    {
        cityId_editor = sharedPreferences.edit();
        cityId_editor.putInt(CITY_ID,CityId);
        cityId_editor.commit();
        cityId_editor.apply();
    }
    public int getCityId(){return sharedPreferences.getInt(CITY_ID,0);}
    public void clear_CityId(){
        cityId_editor = sharedPreferences.edit();
        cityId_editor.clear();
        cityId_editor.commit();
    }

    // ---- CityName Session
    public void createCityNameSession(String CityName)
    {
        cityName_editor = sharedPreferences.edit();
        cityName_editor.putString(CITY_NAME,CityName);
        cityName_editor.commit();
        cityName_editor.apply();
    }
    public String getCityName(){return sharedPreferences.getString(CITY_NAME,"");}
    public void clearCityName(){
        cityName_editor = sharedPreferences.edit();
        cityName_editor.clear();
        cityName_editor.commit();
    }

    public void createTotalQty(int Qty)
    {
        totalQty_editor = sharedPreferences.edit();
        totalQty_editor.putInt(TOTAL_QTY,Qty);
        totalQty_editor.commit();
        totalQty_editor.apply();
    }
    public String getTotalQty(){return  sharedPreferences.getString(TOTAL_QTY,"");}
    public void clearTotalQty()
    {
        totalQty_editor = sharedPreferences.edit();
        totalQty_editor.clear();
        totalQty_editor.commit();
    }

     public void logout()
     {
         clear_UserId();
         clear_UserName();
         clear_UserPassword();
         clear_CityId();
         clearCityName();
     }
}
