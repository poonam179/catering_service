package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.my.catering_service.R;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.model.UserModel;
import com.my.catering_service.model.UserResultModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = PathInterpolatorCompat.MAX_NUM_POINTS;
    LocalStorage localStorage;
    Map<String, Object> UserMap = new HashMap<>();
    public ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    UserModel userModel;
    //private static boolean logincheck ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        localStorage = new LocalStorage(getApplicationContext());
        checklogin();



 //       new Utils().StartActivity(SplashActivity.this,MainActivity.class);
    }
    public void NextActivity(final boolean loginck)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(loginck == false) {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this.getApplicationContext(), MainActivity.class));
                    SplashActivity.this.finish();
                }else {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this.getApplicationContext(),HomeActivity.class));
                    SplashActivity.this.finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
    public void checklogin()
    {
        if(localStorage.getUserId() == "" || localStorage.getUserPassword() == "")
        {
            NextActivity( false);
            //new Utils().StartActivity(SplashActivity.this,LoginActivity.class);
        }
        else {
            UserMap.put("UserName",localStorage.getUserName());
            UserMap.put("Password",localStorage.getUserPassword());


        }Call<UserResultModel> call = RestClient.getRestService(SplashActivity.this).login(UserMap);

        call.enqueue(new Callback<UserResultModel>() {
            @Override
            public void onResponse(Call<UserResultModel> call, Response<UserResultModel> response) {
                if(response.isSuccessful())
                {
                    NextActivity( true);
                    //new Utils().StartActivity(SplashActivity.this,HomeActivity.class);
                }else  if(response.code() == 400)
                {
                    NextActivity( false);
                    Toast.makeText(SplashActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResultModel> call, Throwable t) {
                NextActivity( false);
                Toast.makeText(SplashActivity.this, "Error Inter", Toast.LENGTH_SHORT).show();
            }
        });
    }
}