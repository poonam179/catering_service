package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.catering_service.R;
import com.my.catering_service.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btn_login,btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       btn_login = (Button)findViewById(R.id.btn_login);
       btn_signup = (Button)findViewById(R.id.btn_signup);

       btn_login.setOnClickListener(this);
       btn_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_login:
                    new Utils().StartActivity(this,LoginActivity.class);
                    break;
                case R.id.btn_signup:
                    new Utils().StartActivity(this,SignUpActivity.class);
            }
    }


}