package com.my.catering_service.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.google.android.material.navigation.NavigationView;
import com.my.catering_service.R;
import com.my.catering_service.adapter.DemoInfiniteAdapter;
import com.my.catering_service.model.PackageMasterModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    LocalStorage localStorage;
    Map<String, Object> UserMap = new HashMap<>();
    public ArrayList<PackageMasterModel> packageMasterModelsList = new ArrayList<>();
    TextView lblCityName;
    LoopingViewPager LoopingViewpager;
    DemoInfiniteAdapter adapter;
    ImageView imageViewmanu,toolbar_three_line,toolbar_cart,toolbar_gps;
    DrawerLayout drawerLayout;
    NavigationView nav;
    ActionBarDrawerToggle taggle;
    CardView cardView1,cardView2,cardView3,cardView4;
    //PageIndicatorView indicatorView;

    private int currentDataSet = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        init();
    }
    public void init(){
        localStorage = new LocalStorage(getApplicationContext());

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar_cart =(ImageView)findViewById(R.id.toolbar_cart);
        toolbar_gps =(ImageView)findViewById(R.id.toolbar_gps);
        toolbar_three_line =(ImageView)findViewById(R.id.toolbar_three_line);

        toolbar_gps.setVisibility(View.VISIBLE);
        toolbar_cart.setVisibility(View.VISIBLE);
        toolbar_three_line.setVisibility(View.VISIBLE);

        toolbar_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Utils().StartActivity(DashBoardActivity.this,CartMasterActivity.class);
            }
        });

        lblCityName = (TextView)findViewById(R.id.lbl_cityname);
        lblCityName.setText(localStorage.getCityName());
        lblCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                //Set title
                builder.setTitle("Change City");
                //Set Message
                builder.setMessage("Are you sure you want to Change City ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DashBoardActivity.this.finishAffinity();
                        new Utils().StartActivity(DashBoardActivity.this,HomeActivity.class);
                        //System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        LoopingViewpager = findViewById(R.id.id_LoopingViewpager);
        adapter = new DemoInfiniteAdapter(this, createDummyItems(), true);
        LoopingViewpager.setAdapter(adapter);
        //imageViewmanu = (ImageView)findViewById(R.id.dashboard_manu);

        cardView1 = (CardView)findViewById(R.id.dash_CardView_1);
        cardView2 = (CardView)findViewById(R.id.dash_CardView_2);
        cardView3 = (CardView)findViewById(R.id.dash_CardView_3);


        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);


        //getPackageMaster();
    }
    private ArrayList<Integer> createDummyItems () {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        items.add(2, 3);
//        items.add(3, 4);
//        items.add(4, 5);
//        items.add(5, 6);
        return items;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dash_CardView_1:
                TextView textView = (TextView)findViewById(R.id.dash_TextView_1);
                GetEventValue("1",textView.getText().toString());
                break;
            case R.id.dash_CardView_2:
                TextView textView2 = (TextView)findViewById(R.id.dash_TextView_2);
                GetEventValue("2",textView2.getText().toString());

                break;
            case R.id.dash_CardView_3:
                TextView textView3 = (TextView)findViewById(R.id.dash_TextView_3);
                GetEventValue("3",textView3.getText().toString());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
    public void GetEventValue(String Id,String Name){
        Intent intent = new Intent(getApplicationContext(), Package_View_Activity.class);
        intent.putExtra("Name",Name);
        intent.putExtra("EventID",Id);
        startActivity(intent);


    }
    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    private void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view)
    {
        closeDrawer(drawerLayout);
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        recreate();
    }
    public void ClickDashBoard(View view)
    {
        redirectActivity(this,DashBoardActivity.class);
    }
    public void ClickLogout(View view){
        logout(this);
    }

    private void logout(final Activity activity) {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("LogOut");
        //Set Message
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                localStorage.logout();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void redirectActivity(Activity activity,Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    /*public void getPackageMaster()
    {
        UserMap.put("CityId", localStorage.getCityId());
        Call<PackageResultMasterModel> call = RestClient.getRestService(DashBoardActivity.this).PackageMaster(UserMap);

        call.enqueue(new Callback<PackageResultMasterModel>() {
            @Override
            public void onResponse(Call<PackageResultMasterModel> call, Response<PackageResultMasterModel> response) {
                if(response.isSuccessful())
                {
                    PackageResultMasterModel packageResultMasterModellist = response.body();
                    packageMasterModelsList.add((PackageMasterModel) packageResultMasterModellist.getData());
                }
            }

            @Override
            public void onFailure(Call<PackageResultMasterModel> call, Throwable t) {

            }
        });
    }*/
}