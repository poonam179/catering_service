package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.RelativeLayout;

import com.my.catering_service.R;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.model.UserModel;
import com.my.catering_service.model.UserResultModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LocalStorage localStorage;
    Gson gson = new Gson();
    View progress;
    String userString;
    UserModel userModel;
    private View parentView;
    private RelativeLayout relativeLayout;
    private EditText edit_Emailid, edit_Password;
    private Button btn_Login;
    public static  boolean Login = false;
    Map<String, Object> UserMap = new HashMap<>();
    public ArrayList<UserModel> userModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        localStorage = new LocalStorage(getApplicationContext());
        btn_Login = (Button) findViewById(R.id.btn_login);
        edit_Emailid = (EditText) findViewById(R.id.edit_emailid);
        edit_Password = (EditText) findViewById(R.id.edit_password);
        parentView = findViewById(R.id.mainLayout);

        //Method
        setListener();

    }

    public void setListener() {
        btn_Login.setOnClickListener(this);
    }

    private void checkValidation() {
        final String getEmailId = edit_Emailid.getText().toString();
        final String getPassword = edit_Password.getText().toString();

        if (getEmailId.equals("") || getEmailId.length() == 0 || getPassword.equals("") || getPassword.length() == 0) {
            Snackbar.make(parentView, "Enter both Credential", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.black))
                    .setTextColor(getResources().getColor(R.color.white))
                    .show();
        } else {
            login(getEmailId,getPassword);
        }

    }

    public void login(String EmailId,String Password) {
        //UserMap Set Arguments
            UserMap.put("Username", EmailId);
        UserMap.put("Password", Password);
        //Api call
        Call<UserResultModel> call = RestClient.getRestService(LoginActivity.this).login(UserMap);

        //Api response
        call.enqueue(new Callback<UserResultModel>() {
           @Override
           public void onResponse(Call<UserResultModel> call, Response<UserResultModel> response) {
               Log.d("TAG","Response "+response.body());
              // APIError apiError = ErrorUtils.parseError(response);
              // Toast.makeText(LoginActivity.this,apiError.message(),Toast.LENGTH_SHORT).show();

               if(response.isSuccessful())
               {

                   UserResultModel userResultModel = response.body();
                   //userModelArrayList.addAll(userResultModel.getUserModel());
                   String UserIdTxt = userResultModel.getUserModel().getUserId();
                   String UserPassword = edit_Password.getText().toString();
                   localStorage.createUserIdSession(userResultModel.getUserModel().getUserId());
                   localStorage.createUserNameSession(userResultModel.getUserModel().getEmailId());
                   localStorage.createUserPasswordSession(edit_Password.getText().toString());
                   //Move Next Activity
                   new Utils().StartActivity(LoginActivity.this, HomeActivity.class);
//                   Intent in = new Intent(LoginActivity.this,HomeActivity.class);
//                   startActivity(in);
               }
               else if(response.code() == 400)
               {
                   Snackbar.make(parentView, "Invalid User Detail.", Snackbar.LENGTH_LONG)
                           .setBackgroundTint(getResources().getColor(R.color.black))
                           .setTextColor(getResources().getColor(R.color.white))
                           .show();
               }


           }

           @Override
           public void onFailure(Call<UserResultModel> call, Throwable t) {
               //t.getMessage();
               Toast.makeText(LoginActivity.this," "+t.getMessage(),Toast.LENGTH_LONG).show();
               Log.d("TAG","Response "+t.getMessage());
               Snackbar.make(parentView, "User Detail Not Found", Snackbar.LENGTH_LONG)
                       .setBackgroundTint(getResources().getColor(R.color.black))
                       .setTextColor(getResources().getColor(R.color.white))
                       .show();
           }
       });

    }

    @Override
    public void onClick(View v) {
        //Method
        checkValidation();
       /* if(Login==true)
        {
            new Utils().StartActivity(this, HomeActivity.class);
        }*/
    }
}