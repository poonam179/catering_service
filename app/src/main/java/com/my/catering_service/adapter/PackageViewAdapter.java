package com.my.catering_service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.catering_service.R;
import com.my.catering_service.model.PackageMasterModel;
import com.my.catering_service.model.SubPackageMasterModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PackageViewAdapter extends RecyclerView.Adapter<PackageViewAdapter.ViewHolder>{
    List<PackageMasterModel> packageMasterModelList;
    SubPackageViewAdapter subPackageViewAdapter;
    Context context;


    public PackageViewAdapter(List<PackageMasterModel> packageMasterModelList, Context context) {
        this.packageMasterModelList = packageMasterModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_main_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewAdapter.ViewHolder holder, int position) {
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.subpackage_recyclerview.setLayoutManager(HorizontalLayout);
        PackageMasterModel item = packageMasterModelList.get(position);
        holder.package_title.setText(item.getPcmName());

        List<SubPackageMasterModel> subPackageMasterModelsList = item.getSubPackageList();
        subPackageViewAdapter = new SubPackageViewAdapter(subPackageMasterModelsList,context);
        holder.subpackage_recyclerview.setAdapter(subPackageViewAdapter);

    }

    @Override
    public int getItemCount() {
        return packageMasterModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView package_title;
        private RecyclerView subpackage_recyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            package_title = (TextView)itemView.findViewById(R.id.txt_package_title);
            subpackage_recyclerview = (RecyclerView)itemView.findViewById(R.id.RecycleView_SubPackage);
        }
    }
}
