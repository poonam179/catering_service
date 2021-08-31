package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.catering_service.R;
import com.my.catering_service.adapter.CartViewAdapter;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.model.CartMasterModel;
import com.my.catering_service.model.CartMasterResultModel;
import com.my.catering_service.model.CartMasterViewModel;
import com.my.catering_service.model.CartMasterViewResultModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartMasterActivity extends AppCompatActivity {
    LocalStorage localStorage;
    private RecyclerView cartmasterview;
    CartViewAdapter cartViewAdapter;
    Map<String,Object> CartMasterMap = new HashMap<>();
    RecyclerView RecycleView_CartMaster_Main;
    LinearLayoutManager linearLayoutManager;
    ImageView back_btn_toolbar;
    TextView tool_txt_lblName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_master);
        init();
        getCartList();
    }
    public void init(){
        back_btn_toolbar = (ImageView)findViewById(R.id.toolbar_back_arrow);
        back_btn_toolbar.setVisibility(View.VISIBLE);
        tool_txt_lblName = (TextView)findViewById(R.id.lbl_cityname);
        localStorage = new LocalStorage(getApplicationContext());
        tool_txt_lblName.setText("Cart View");
        RecycleView_CartMaster_Main = (RecyclerView)findViewById(R.id.RecycleView_CartMaster_Main);
        RecycleView_CartMaster_Main.setLayoutManager(new LinearLayoutManager(this));
        back_btn_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Utils().StartActivity(CartMasterActivity.this,DashBoardActivity.class);
            }
        });

    }
    public void getCartList()
    {
        CartMasterMap.put("UserId",localStorage.getUserId());
        //Api call
        Call<CartMasterViewResultModel> call = RestClient.getRestService(CartMasterActivity.this).GetCartView(CartMasterMap);

        call.enqueue(new Callback<CartMasterViewResultModel>() {
            @Override
            public void onResponse(Call<CartMasterViewResultModel> call, Response<CartMasterViewResultModel> response) {
                if(response.isSuccessful())
                {
                    CartMasterViewResultModel cartMasterViewResultModel = response.body();
                    List<CartMasterViewModel> cartMasterViewModels = cartMasterViewResultModel.getData();
                    cartViewAdapter = new CartViewAdapter(cartMasterViewModels,getApplicationContext());
                    RecycleView_CartMaster_Main.setAdapter(cartViewAdapter);
                    //RecycleView_CartMaster_Main.noti();
                }
            }

            @Override
            public void onFailure(Call<CartMasterViewResultModel> call, Throwable t) {

            }
        });
    }
}