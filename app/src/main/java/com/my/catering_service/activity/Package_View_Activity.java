package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.my.catering_service.adapter.PackageViewAdapter;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.databinding.ActivityPackageViewBinding;
import com.my.catering_service.model.PackageMasterModel;
import com.my.catering_service.model.PackageResultMasterModel;
import com.my.catering_service.model.UserModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Package_View_Activity extends AppCompatActivity {

    public ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    LocalStorage localStorage;
    Map<String, Object> UserMap = new HashMap<>();
    public ArrayList<PackageMasterModel> packageMasterModelsList = new ArrayList<>();
    public PackageViewAdapter packageViewAdapter;
    private String Name,EventID;
    ActivityPackageViewBinding activityBoxPackageViewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBoxPackageViewBinding = ActivityPackageViewBinding.inflate(getLayoutInflater());
        setContentView(activityBoxPackageViewBinding.getRoot());
        init();
        localStorage = new LocalStorage(getApplicationContext());
        ChecksaveinstanceState(savedInstanceState);
        getPackageMaster();
    }
    public void init(){
        activityBoxPackageViewBinding.topapptollbar.toolbarBackArrow.setVisibility(View.VISIBLE);
        activityBoxPackageViewBinding.topapptollbar.toolbarCart.setVisibility(View.VISIBLE);
        activityBoxPackageViewBinding.topapptollbar.toolbarBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Utils().StartActivity(Package_View_Activity.this,DashBoardActivity.class);
            }
        });
        activityBoxPackageViewBinding.RecycleViewMainPackage.setLayoutManager(new LinearLayoutManager(this));
    }
    private void ChecksaveinstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Name = null;
            } else {
                EventID =  extras.getString("EventID");
                activityBoxPackageViewBinding.topapptollbar.lblCityname.setText(extras.getString("Name"));
            }
        }
        Toast.makeText(this, ""+EventID + " "+ Name, Toast.LENGTH_SHORT).show();
    }

    public void getPackageMaster()
    {
        UserMap.put("CityId", localStorage.getCityId());
        UserMap.put("EventID",EventID);
        Call<PackageResultMasterModel> call = RestClient.getRestService(Package_View_Activity.this).PackageMaster(UserMap);

        call.enqueue(new Callback<PackageResultMasterModel>() {
            @Override
            public void onResponse(Call<PackageResultMasterModel> call, Response<PackageResultMasterModel> response) {
                if(response.isSuccessful())
                {
                    PackageResultMasterModel packageResultMasterModel = response.body();
                    packageMasterModelsList.addAll(packageResultMasterModel.getData());
                    packageViewAdapter = new PackageViewAdapter(packageMasterModelsList,Package_View_Activity.this);
                    activityBoxPackageViewBinding.RecycleViewMainPackage.setAdapter(packageViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<PackageResultMasterModel> call, Throwable t) {

            }
        });
    }

}