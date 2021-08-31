package com.my.catering_service.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.catering_service.R;
import com.my.catering_service.model.ItemMasterModel;
import com.my.catering_service.model.PackageDtlModel;
import com.my.catering_service.model.PackageManuModel;
import com.my.catering_service.model.SubPackageMasterModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PackageManuViewAdapter extends RecyclerView.Adapter<PackageManuViewAdapter.ViewHolder> {
    List<PackageDtlModel> packageDtlModelList;
    PackageManuItemViewAdapter packageManuItemViewAdapter;
    Context context;

    public PackageManuViewAdapter(List<PackageDtlModel> packageManuModelList, Context context) {
        this.packageDtlModelList = packageManuModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageManuViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pck_category_view_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageManuViewAdapter.ViewHolder holder, int position) {
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        holder.pck_item_view_recycle.setLayoutManager(HorizontalLayout);
        PackageDtlModel item = packageDtlModelList.get(position);
        holder.pck_title.setText(item.getCat_Name());
        holder.pck_Qty.setText(String.valueOf(item.getQty()));


        List<ItemMasterModel> itemMasterList = item.getItemMasterList();
        packageManuItemViewAdapter = new PackageManuItemViewAdapter(itemMasterList,context);
        holder.pck_item_view_recycle.setAdapter(packageManuItemViewAdapter);
        packageManuItemViewAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return packageDtlModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pck_title,pck_Qty;
        private RecyclerView pck_item_view_recycle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pck_title = (TextView)itemView.findViewById(R.id.txt_pck_manu_title);
            pck_Qty = (TextView)itemView.findViewById(R.id.txt_pck_manu_qty);
            pck_item_view_recycle = (RecyclerView)itemView.findViewById(R.id.RecycleView_Pck_Item);
        }
    }
}
