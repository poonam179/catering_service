package com.my.catering_service.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.my.catering_service.R;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.model.RegistrationModel;
import com.my.catering_service.model.UserModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VerifyOTPActivity extends AppCompatActivity implements View.OnClickListener {

    private String verificationId;
    private String MobileNo,emailId,firstName,lastName;
    private TextView txtMobile;
    private EditText edit_OTPverifyNo;
    //EditText txtOTPverify;

    LocalStorage localStorage;
    Gson gson = new Gson();
    View progress;
    String userString;
    UserModel userModel;
    public static  boolean Login = false;
    Map<String, Object> UserMap = new HashMap<>();
    //public ArrayList<UserModel> userModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        edit_OTPverifyNo = (EditText) findViewById(R.id.VerifiyOTPNo);

        txtMobile = (TextView) findViewById(R.id.txtMobile);

        //String MobileNo = "";
        //call Method
        ChecksaveinstanceState(savedInstanceState);

        final Button btnVerify = (Button) findViewById(R.id.VerifyOTPbtn);
        btnVerify.setOnClickListener(this);


    }

    private void ChecksaveinstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                MobileNo = null;
            } else {
                MobileNo = extras.getString("mobile");
                verificationId = extras.getString("verificationID");
                emailId = extras.getString("emailId");
                firstName = extras.getString("firstName");
                lastName = extras.getString("lastName");


            }
        }
        txtMobile.setText(MobileNo);
    }

    @Override
    public void onClick(View view) {
        OTPVerification();
    }

    private void OTPVerification() {
        String Code = edit_OTPverifyNo.getText().toString();
        if (verificationId != null) {
            try {
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                        verificationId, Code
                );
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    //new Utils().StartActivity(VerifyOTPActivity.this, HomeActivity.class);
                                    registration();
//                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
                                } else {
                                    Toast.makeText(VerifyOTPActivity.this, "The Verification code entered was invalid", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (Exception ex) {
                Toast.makeText(VerifyOTPActivity.this, " " + ex, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void registration()
    {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setEmailId(emailId);
        registrationModel.setMobileNo(MobileNo);
        registrationModel.setFirstName(firstName);
        registrationModel.setLastName(lastName);
        registrationModel.setPassword("5291");
        //Api call
        Call<RegistrationModel> call = RestClient.getRestService(VerifyOTPActivity.this).createUser(registrationModel);

        //Api response
        call.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                Log.d("TAG","Response "+response.body());
                // APIError apiError = ErrorUtils.parseError(response);
                // Toast.makeText(LoginActivity.this,apiError.message(),Toast.LENGTH_SHORT).show();

                if(response.isSuccessful())
                {

                    RegistrationModel userResultModel = response.body();
                    //Move Next Activity
                    new Utils().StartActivity(VerifyOTPActivity.this, HomeActivity.class);
//                   Intent in = new Intent(LoginActivity.this,HomeActivity.class);
//                   startActivity(in);
                }
                else if(response.code() == 400)
                {
//                    Snackbar.make(parentView, "Invalid User Detail.", Snackbar.LENGTH_LONG)
//                            .setBackgroundTint(getResources().getColor(R.color.black))
//                            .setTextColor(getResources().getColor(R.color.white))
//                            .show();
                }


            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                //t.getMessage();
                //Toast.makeText(LoginActivity.this," "+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("TAG","Response "+t.getMessage());
//                Snackbar.make(parentView, "User Detail Not Found", Snackbar.LENGTH_LONG)
//                        .setBackgroundTint(getResources().getColor(R.color.black))
//                        .setTextColor(getResources().getColor(R.color.white))
//                        .show();
            }
        });
    }
}


    //   @Override
//    public void onReceive(Context context, Intent intent) {
//        SmsManager[] smsManagers = Telephony.Sms.Intents.getMessagesFromIntent(intent);
//        for(SmsMessage smsMessage : smsManagers){
//            String message_body = smsMessage.getMessageBody();
//            String getOTP = message_body.split(":")[1];
//
//        }
//    }
//}