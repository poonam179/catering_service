package com.my.catering_service.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.my.catering_service.R;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.model.CheckUserModel;
import com.my.catering_service.model.CheckUserResultModel;
import com.my.catering_service.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_firstName, edit_lastName, edit_emailid, edit_mobileno;
    private Button btn_ragister;
    private FirebaseAuth auth;
    private final static String msg="";

    private View parentView;

    Gson gson = new Gson();
    View progress;
    String userString;
    UserModel userModel;
    Map<String, Object> UserMap = new HashMap<>();
    public ArrayList<UserModel> userModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edit_firstName = findViewById(R.id.edit_firstname);
        edit_lastName = findViewById(R.id.edit_lastname);
        edit_emailid = findViewById(R.id.edit_emailid);
        edit_mobileno = findViewById(R.id.edit_mobileno);
        btn_ragister = findViewById(R.id.btn_register);
        auth = FirebaseAuth.getInstance();
        parentView = findViewById(R.id.SignActivity);

        btn_ragister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                CheckUser(edit_mobileno);
                //Sent_OTPVerification(edit_mobileno);
                break;
        }
    }
    public void CheckUser(final EditText mobilenumber)
    {
        UserMap.put("EmailId","");
        UserMap.put("mobileNo",mobilenumber.getText().toString());
        Call<CheckUserResultModel> call = RestClient.getRestService(SignUpActivity.this).registration(UserMap);

        call.enqueue(new Callback<CheckUserResultModel>() {
            @Override
            public void onResponse(Call<CheckUserResultModel> call, Response<CheckUserResultModel> response) {
                if(response.isSuccessful())
                {
                    CheckUserResultModel checkUserResultModel = response.body();
                    CheckUserModel checkUserModel = checkUserResultModel.getData();
                    if(Integer.parseInt(checkUserModel.getUserId())  == 0 )
                    {
                        Sent_OTPVerification(mobilenumber);
                    }else{
                        Snackbar.make(parentView, "your mobile no already exists!.", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(getResources().getColor(R.color.black))
                                .setTextColor(getResources().getColor(R.color.white))
                                .show();
                    }
                }
                else if(response.code() == 400)
                {
                    Sent_OTPVerification(mobilenumber);
                }
            }

            @Override
            public void onFailure(Call<CheckUserResultModel> call, Throwable t) {
                Snackbar.make(parentView, "Invalid User Details", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.black))
                        .setTextColor(getResources().getColor(R.color.white))
                        .show();
            }
        });
    }
    public void Sent_OTPVerification(final EditText mobileNumber) {
        if (mobileNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Mobile No", Toast.LENGTH_SHORT).show();
        } else {
            try {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + mobileNumber.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        SignUpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Log.d("VERIFICATION", "On Verification Completed" + phoneAuthCredential +"    "+phoneAuthCredential.getSmsCode());
                                //Toast.makeText(getApplicationContext(), ""+phoneAuthCredential, Toast.LENGTH_SHORT).show();
                                String code = phoneAuthCredential.getSmsCode();
                                Log.d("SMS", "On SMS Code" + code);

                                if (phoneAuthCredential != null )
                                    signInWithPhoneAuthCredential(phoneAuthCredential);
                                else {
                                    Toast.makeText(
                                            SignUpActivity.this,
                                            "Something went wrong, please try later.",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                }

                                //signInWithPhoneAuthCredential(phoneAuthCredential);
                                /*if (code != null) {
                                    Toast.makeText(getApplicationContext(), "not verify otp", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),"completed",Toast.LENGTH_SHORT).show();

                                    auth.signInWithCredential(phoneAuthCredential)
                                            .addOnCompleteListener(this
                                                    , new OnCompleteListener<AuthResult>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            final FirebaseUser user = auth.getCurrentUser();
                                                            if (task.isSuccessful()) {
                                                                FirebaseUser user1 = task.getResult().getUser();
                                                            }
                                                            else{
                                                                if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){

                                                                }
                                                            }
                                                        }
                                                    });
                                }*/
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                sentUserData(verificationID);
                            }
                        }
                );
            } catch (Exception ex) {
                Log.d("ERROR", ex.getMessage());
                Toast.makeText(this, "" + ex, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            AuthResult result = task.getResult();
                            FirebaseUser user = task.getResult().getUser();

                            FirebaseAuth.getInstance().signOut();
                            Log.d("Success", "signInWithCredential:success "+user);
                            // Update UI
                            Toast.makeText(SignUpActivity.this,"hello "+user.getIdToken(task.isSuccessful()),Toast.LENGTH_SHORT).show();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("Failure", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    public void sentUserData(String VerificationCode) {
        try {
            Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
            intent.putExtra("mobile", edit_mobileno.getText().toString());
            intent.putExtra("firstName", edit_firstName.getText().toString());
            intent.putExtra("lastName", edit_lastName.getText().toString());
            intent.putExtra("emailId", edit_emailid.getText().toString());
            intent.putExtra("verificationID", VerificationCode);
            startActivity(intent);
        } catch (Exception e) {
            e.getMessage();
        }

    }
}