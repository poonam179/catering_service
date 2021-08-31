package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.my.catering_service.R;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.model.CartMasterModel;
import com.my.catering_service.model.CartMasterResultModel;
import com.my.catering_service.model.PackageManuModel;
import com.my.catering_service.model.R_Package_DtlModel;
import com.my.catering_service.model.UserResultModel;
import com.my.catering_service.util.Utils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;

public class Select_Member_Activity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    CartMasterModel cartMasterModel = new CartMasterModel();
    ArrayList<R_Package_DtlModel> R_Package_DtlModelArrayList = new ArrayList<>();
    ArrayList<PackageManuModel> PackageManuList = new ArrayList<>();
    int MiniMember,SPCMID,PCMID;
    double SPCMPrice;
    TextInputLayout txt_date,txt_layout_member;
    EditText txt_edit_Member;
    TextView txt_edit_Date,tool_txt_lblName;
    Intent intent;
    Button btn_AddToMenuCart;
    ImageView back_btn_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__member);
        intent = getIntent();
        init();
        toolbar();
    }
    public void toolbar(){
        back_btn_toolbar = (ImageView)findViewById(R.id.toolbar_back_arrow);
        tool_txt_lblName = (TextView)findViewById(R.id.lbl_cityname);
        back_btn_toolbar.setVisibility(View.VISIBLE);
        tool_txt_lblName.setText("Select Member");
        back_btn_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Utils().StartActivity(Select_Member_Activity.this,DashBoardActivity.class);
            }
        });
    }

    public void init()
    {
        Gson gson = new Gson();
        cartMasterModel = gson.<CartMasterModel>fromJson(intent.getStringExtra("ArrayList"),CartMasterModel.class);
        //cartMasterModel = (CartMasterModel)intent.getParcelableExtra("ArrayList");
        //R_Package_DtlModelArrayList = (ArrayList<R_Package_DtlModel>)intent.getSerializableExtra("ArrayList");
        MiniMember = Integer.valueOf(intent.getStringExtra("MiniMember"));
        SPCMID = cartMasterModel.getSPCMID();
        PCMID = cartMasterModel.getPCMID();
        txt_layout_member = (TextInputLayout)findViewById(R.id.txt_layout_Member);
        //SPCMPrice = Integer.valueOf(intent.getStringExtra("SPCMPrice"));
        //SPCMID = Integer.valueOf(intent.getStringExtra("SPCMID"));
        //PCMID = Integer.valueOf(intent.getStringExtra("PCMID"));


        txt_date = (TextInputLayout)findViewById(R.id.edit_date);
        txt_edit_Member = (EditText)findViewById(R.id.edit_Member);
        btn_AddToMenuCart = (Button)findViewById(R.id.btn_AddToMenuCart);

        txt_edit_Date = (TextView)findViewById(R.id.txt_edit_Date);
        txt_edit_Member.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(txt_edit_Member.getText().toString() != "") {
                        if (MiniMember <= Integer.valueOf(txt_edit_Member.getText().toString()))
                            txt_layout_member.setErrorEnabled(false);
                        else
                            txt_layout_member.setError("Pls Enter The minimum Member Of " + MiniMember);
                    }
                }
            }
        });

        txt_edit_Member.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    if (MiniMember <= Integer.valueOf(txt_edit_Member.getText().toString()))
                        txt_layout_member.setErrorEnabled(false);
                    else
                        txt_layout_member.setError("Pls Enter The minimum Member Of " + MiniMember);
                }
                else
                    txt_layout_member.setError("Pls Enter The minimum Member Of " + MiniMember);
            }
        });
        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        btn_AddToMenuCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!txt_edit_Member.getText().toString().isEmpty() ) {
            if (MiniMember <= Integer.valueOf(txt_edit_Member.getText().toString())) {
                if (txt_edit_Date.getText().toString() != "") {
                    SaveCartData();
                }
            }
        }
        else {
            txt_layout_member.setError("Pls Enter The minimum Member Of " + MiniMember);
        }
    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                Select_Member_Activity.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = (month+1)+ "/" + dayOfMonth  + "/" + year;
        txt_edit_Date.setText(date);
    }

    private void SaveCartData() {
        Map<String, Object> UserMap = new HashMap<>();

        Gson gson = new Gson();
        String ModelsData = gson.toJson(cartMasterModel);
        UserMap.put("ModelsData",ModelsData);
        cartMasterModel.setC_Date(String.valueOf(txt_edit_Date.getText()));
        cartMasterModel.setNoOfMember(Integer.valueOf(String.valueOf(txt_edit_Member.getText())));
        //API Call
        retrofit2.Call<CartMasterModel> call = RestClient.getRestService(Select_Member_Activity.this).CartMaster(cartMasterModel);

        //Api response
        call.enqueue(new Callback<CartMasterModel>() {
            @Override
            public void onResponse(retrofit2.Call<CartMasterModel> call, Response<CartMasterModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Select_Member_Activity.this, "Save Cart Order", Toast.LENGTH_SHORT).show();
                    new Utils().StartActivity(Select_Member_Activity.this,CartMasterActivity.class);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CartMasterModel> call, Throwable t) {
                Toast.makeText(Select_Member_Activity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}