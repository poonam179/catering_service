package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.my.catering_service.R;
import com.my.catering_service.adapter.AutoCompleteAdapter;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.databinding.ActivityHomeBinding;
import com.my.catering_service.model.CityMasterModel;
import com.my.catering_service.model.CityResultMasterModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    LocalStorage localStorage;
    private View parentView;
    EditText editText;
    Spinner spinner;
    Button btnSelectCity;
    private static int indexofspinner;
    String city_name;
    int city_id;
    public ArrayList<CityMasterModel> cityMasterModelArrayList = new ArrayList<>();
    private AutoCompleteAdapter autoCompleteAdapter;
    ActivityHomeBinding activityHomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());

        //parentView = findViewById(R.id.ActivityHome);

        GetCityList();
        initUI();

    }
    private void initUI() {
        localStorage = new LocalStorage(getApplicationContext());

        //spinner = (Spinner)findViewById(R.id.simpleSpinner);
        //spinner.setOnItemSelectedListener(this);
        activityHomeBinding.simpleSpinner.setOnItemSelectedListener(this);
        //btnSelectCity = (Button)findViewById(R.id.btn_city);
        //btnSelectCity.setOnClickListener(this);
        activityHomeBinding.btnCity.setOnClickListener(this);

    }

    public void GetCityList() {

        //Api call
        Call<CityResultMasterModel> call = RestClient.getRestService(HomeActivity.this).CityMasterList();

        //Api response
        call.enqueue(new Callback<CityResultMasterModel>() {
            @Override
            public void onResponse(Call<CityResultMasterModel> call, Response<CityResultMasterModel> response) {
                Log.d("TAG","Response "+response.body());
                // APIError apiError = ErrorUtils.parseError(response);
                // Toast.makeText(LoginActivity.this,apiError.message(),Toast.LENGTH_SHORT).show();

                if(response.isSuccessful())
                {

                    CityResultMasterModel cityResultMasterModel = response.body();
                    cityMasterModelArrayList.addAll(cityResultMasterModel.getCityList());

                    autoCompleteAdapter = new AutoCompleteAdapter(getApplicationContext(),cityMasterModelArrayList);
                    activityHomeBinding.simpleSpinner.setAdapter(autoCompleteAdapter);

                }
                else if(response.code() == 400)
                {

                    Snackbar.make(activityHomeBinding.ActivityHome, "City Not Found..", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.black))
                            .setTextColor(getResources().getColor(R.color.white))
                            .show();
                }


            }

            @Override
            public void onFailure(Call<CityResultMasterModel> call, Throwable t) {
                //t.getMessage();
                Toast.makeText(HomeActivity.this," "+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("TAG","Response "+t.getMessage());
                Snackbar.make(activityHomeBinding.ActivityHome, "Data Not Found", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.black))
                        .setTextColor(getResources().getColor(R.color.white))
                        .show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        indexofspinner = position;
        city_id=cityMasterModelArrayList.get(position).getCityId();
        city_name = cityMasterModelArrayList.get(position).getCityName();

        storeDrValue(city_name,city_id);
    }
    public void storeDrValue(String name,int id){
        this.city_name=name;
        this.city_id=id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        localStorage.createCityIDSession(city_id);
        localStorage.createCityNameSession(city_name);
        new Utils().StartActivity(HomeActivity.this,DashBoardActivity.class);
    }
}