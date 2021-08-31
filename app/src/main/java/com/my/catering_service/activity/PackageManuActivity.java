package com.my.catering_service.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.my.catering_service.R;
import com.my.catering_service.adapter.PackageManuViewAdapter;
import com.my.catering_service.api.clients.RestClient;
import com.my.catering_service.databinding.ActivityPackageManuBinding;
import com.my.catering_service.model.CartMasterModel;
import com.my.catering_service.model.ItemMasterModel;
import com.my.catering_service.model.PackageDtlModel;
import com.my.catering_service.model.PackageManuModel;
import com.my.catering_service.model.PackageManuResultModel;
import com.my.catering_service.model.R_Package_DtlModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;
import com.my.catering_service.util.Utils;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageManuActivity extends AppCompatActivity implements View.OnClickListener {


    LocalStorage localStorage;
    private View parentView;
    public PackageManuModel PackageManuModelArrayList;
    ActivityPackageManuBinding activityPackageManuBinding;
    PackageManuViewAdapter packageManuViewAdapter;
    private RecyclerView packageMenuRecyclerView;
    String SPCMID;
    Map<String, Object> UserMap = new HashMap<>();
    TextView txtPckTitle,txtPckPrice,txtPckMember,tool_txt_lblName;
    FloatingActionButton Btn_Pck_Manu;
    ImageView back_btn_toolbar;
    int Totalqty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_manu);
        Bundle extras = getIntent().getExtras();
        SPCMID = extras.getString("Id");
        packageMenuRecyclerView = (RecyclerView)findViewById(R.id.RecycleView_Package_Manu_Main);
        packageMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //activityPackageManuBinding.RecycleViewPackageManuMain.setLayoutManager(new LinearLayoutManager(this));
        init();
        toolbar();
        GetPackageManuList();

        //Toast.makeText(this,"spcmId = "+SPCMID,Toast.LENGTH_LONG).show();
    }
    public void toolbar()
    {
        back_btn_toolbar = (ImageView)findViewById(R.id.toolbar_back_arrow);
        back_btn_toolbar.setVisibility(View.VISIBLE);
        tool_txt_lblName = (TextView)findViewById(R.id.lbl_cityname);
        tool_txt_lblName.setText("PackageManu");
        back_btn_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Utils().StartActivity(PackageManuActivity.this,DashBoardActivity.class);

            }
        });
    }
    public void init(){
        localStorage = new LocalStorage(getApplicationContext());
        txtPckTitle = (TextView)findViewById(R.id.txt_pck_title);
        txtPckPrice = (TextView)findViewById(R.id.txt_pck_price);
        txtPckMember = (TextView)findViewById(R.id.txt_pck_member);
        Btn_Pck_Manu = (FloatingActionButton) findViewById(R.id.fab_pck_manu);
        //Btn_Pck_Manu.hide();
        Btn_Pck_Manu.setOnClickListener( this);

        //activityPackageManuBinding.topapptollbar.toolbarBackArrow.setVisibility(View.VISIBLE);
        //activityPackageManuBinding.topapptollbar.toolbarCart.setVisibility(View.VISIBLE);
        //activityPackageManuBinding.topapptollbar.toolbarBackArrow.setOnClickListener(new View.OnClickListener() {

    }
    public void GetPackageManuList() {
        UserMap.put("spcmID",SPCMID);
        //Api call
        Call<PackageManuResultModel> call = RestClient.getRestService(PackageManuActivity.this).PackageManu(UserMap);

        //Api response
        call.enqueue(new Callback<PackageManuResultModel>() {
            @Override
            public void onResponse(Call<PackageManuResultModel> call, Response<PackageManuResultModel> response) {
                Log.d("TAG","Response "+response.body());

                if(response.isSuccessful())
                {
                    PackageManuResultModel packageManuResultList = response.body();
                    Toast.makeText(PackageManuActivity.this, "Hello "+packageManuResultList, Toast.LENGTH_SHORT).show();
                    PackageManuModelArrayList = packageManuResultList.getData();
                    String SPCMName,SPCMPrice,SPCMMember;
                    txtPckTitle.setText(PackageManuModelArrayList.getSpcmName());
                    txtPckPrice.setText(String.valueOf(PackageManuModelArrayList.getSpcmPrice()));
                    txtPckMember.setText(String.valueOf(PackageManuModelArrayList.getMiniMember()));

                    // ---- Total Qty ---- \\
                    Totalqty = 0;
                    for(int i=0;i<PackageManuModelArrayList.getPackage_DtlList().size();i++)
                    {
                        Totalqty += PackageManuModelArrayList.getPackage_DtlList().get(i).getQty();
                    }
                    //localStorage.createTotalQty(Totalqty);
                    // ----- Call Adapter ------ \\
                    packageManuViewAdapter = new PackageManuViewAdapter(PackageManuModelArrayList.getPackage_DtlList(),getApplicationContext());
                    packageMenuRecyclerView.setAdapter(packageManuViewAdapter);
                    packageManuViewAdapter.notifyDataSetChanged();


                }
                else if(response.code() == 400)
                {
                    Snackbar.make(activityPackageManuBinding.ActivityPackageManu, "SPCMID Not Found..", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.black))
                            .setTextColor(getResources().getColor(R.color.white))
                            .show();
                }
            }

            @Override
            public void onFailure(Call<PackageManuResultModel> call, Throwable t) {
                Toast.makeText(PackageManuActivity.this," "+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("TAG","Response "+t.getMessage());
                Snackbar.make(activityPackageManuBinding.ActivityPackageManu, "Data Not Found", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.black))
                        .setTextColor(getResources().getColor(R.color.white))
                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        ArrayList<ItemMasterModel> ItemMasterFinalList = new ArrayList<>();

        String RequirdItemSelete = "";

        for(int i = 0;i<PackageManuModelArrayList.getPackage_DtlList().size();i++)
        {
            int counts = 0;
            for(int j = 0;j<PackageManuModelArrayList.getPackage_DtlList().get(i).getItemMasterList().size();j++)
            {
                if(PackageManuModelArrayList.getPackage_DtlList().get(i).getItemMasterList().get(j).isFlag()) {
                    counts++;
                    ItemMasterFinalList.add(PackageManuModelArrayList.getPackage_DtlList().get(i).getItemMasterList().get(j));
                }
            }
            if(counts < PackageManuModelArrayList.getPackage_DtlList().get(i).getQty())
            {
                RequirdItemSelete = PackageManuModelArrayList.getPackage_DtlList().get(i).getCat_Name();
                break;
            }
        }
        if(Totalqty == ItemMasterFinalList.size())
        {
            CartMasterModel cartMasterModelsData = new CartMasterModel();

            Intent intent = new Intent(getApplicationContext(),Select_Member_Activity.class);
            //Bundle bundle = new Bundle();
            //bundle.putParcelable("ArrayList",ItemMasterFinalList);

            cartMasterModelsData = R_PackageModelConvertList(ItemMasterFinalList);
            Gson gson = new Gson();
            String CartData = gson.toJson(cartMasterModelsData);
            intent.putExtra("ArrayList", CartData);
            //intent.putExtra("ArrayList",(Serializable)r_package_dtlModelslist);
            //intent.putExtra("PackageManuList",(Serializable)PackageManuModelArrayList);
            /*intent.putExtra("SPCMPrice",String.valueOf(PackageManuModelArrayList.getSpcmPrice()));
            intent.putExtra("SPCMID",String.valueOf(PackageManuModelArrayList.getSpcmid()));
            intent.putExtra("PCMID",String.valueOf(PackageManuModelArrayList.getPcmid()));
            intent.putExtra("MiniMember",String.valueOf(PackageManuModelArrayList.getMiniMember()));
            */
            intent.putExtra("MiniMember",String.valueOf(PackageManuModelArrayList.getMiniMember()));
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Select "+ RequirdItemSelete +" Item", Toast.LENGTH_SHORT).show();
        }
    }
    public CartMasterModel R_PackageModelConvertList(ArrayList<ItemMasterModel> ItemMasterFinalList)
    {
        CartMasterModel cartMasterModels = new CartMasterModel();
        cartMasterModels.setCartId(0);
        cartMasterModels.setC_Date("");
        cartMasterModels.setC_Time("");
        cartMasterModels.setNoOfMember(0);
        cartMasterModels.setPackagePrice(PackageManuModelArrayList.getSpcmPrice());
        cartMasterModels.setPCMID(PackageManuModelArrayList.getPcmid());
        cartMasterModels.setSPCMID(PackageManuModelArrayList.getSpcmid());
        cartMasterModels.setUserID(Integer.valueOf(localStorage.getUserId()));


        ArrayList<R_Package_DtlModel> r_package_dtlModelfinallist = new ArrayList<>();
        List<R_Package_DtlModel> r_package_dtlModelList = new ArrayList<>();
        for(int i =0;i<ItemMasterFinalList.size();i++)
        {
            r_package_dtlModelList.clear();
            r_package_dtlModelList.add(new R_Package_DtlModel(0,0,0));
            r_package_dtlModelList.get(0).setR_PCM_DtlID(0);
            r_package_dtlModelList.get(0).setCartID(0);
            r_package_dtlModelList.get(0).setItemID(ItemMasterFinalList.get(i).getItemID());
            r_package_dtlModelfinallist.addAll(r_package_dtlModelList);
            //ItemIDs.add(String.valueOf(ItemMasterFinalList.get(i).getItemID()));
        }
        cartMasterModels.setReservation_Package_DtlList(r_package_dtlModelfinallist);
        return cartMasterModels;
    }
}