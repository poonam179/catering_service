package com.my.catering_service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.catering_service.R;
import com.my.catering_service.model.ItemMasterModel;
import com.my.catering_service.model.PackageDtlModel;
import com.my.catering_service.util.LocalStorage.LocalStorage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PackageManuItemViewAdapter extends RecyclerView.Adapter<PackageManuItemViewAdapter.ViewHolder> {
    int counter = 0;
    List<ItemMasterModel> ItemMasterModelList;
    List<PackageDtlModel> packageDtlModelList;
    Context context;

    public PackageManuItemViewAdapter(List<ItemMasterModel> itemMasterModelList, Context context) {
        ItemMasterModelList = itemMasterModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageManuItemViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pck_item_view_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ItemMasterModel item = ItemMasterModelList.get(position);
        //PackageDtlModel packageDtlModel = packageDtlModelList.get(position);
        holder.txt_pck_Item.setText(item.getItemName());
        holder.LinerL_pck_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkbox_pck_item.isChecked() == false) {
                    if (counter < ItemMasterModelList.get(position).getQty()) {
                        holder.checkbox_pck_item.setChecked(true);
                        counter++;
                    }
                    else{
                        holder.checkbox_pck_item.setChecked(false);
                        Toast.makeText(context, "Maximum Value Selected", Toast.LENGTH_SHORT).show();
                    }
                } else if (holder.checkbox_pck_item.isChecked() == true) {
                    holder.checkbox_pck_item.setChecked(false);
                    counter--;
                }
            }
        });
        //holder.checkbox_pck_item.setChecked(false);

        holder.checkbox_pck_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(holder.checkbox_pck_item.isChecked())
                {
                    item.setFlag(true);
                }else
                {
                    item.setFlag(false);
                }
            }
        });
        if(item.isFlag())
        {
            holder.checkbox_pck_item.setChecked(true);
        }
        else{
            holder.checkbox_pck_item.setChecked(false);
        }

        holder.checkbox_pck_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.checkbox_pck_item.isChecked()) {
                    if (counter < ItemMasterModelList.get(position).getQty()) {
                        holder.checkbox_pck_item.setChecked(true);
                        counter++;
                    }
                    else{
                        holder.checkbox_pck_item.setChecked(false);
                        Toast.makeText(context, "Maximum Value Selected", Toast.LENGTH_SHORT).show();

                    }
                } else if (holder.checkbox_pck_item.isChecked() == false) {
                    //holder.checkbox_pck_item.setChecked(false);
                    counter--;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemMasterModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_pck_Item;
        private CheckBox checkbox_pck_item;
        private LinearLayout LinerL_pck_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_pck_Item = (TextView) itemView.findViewById(R.id.txt_pck_item);
            checkbox_pck_item = (CheckBox) itemView.findViewById(R.id.checkbox_pck_item);
            LinerL_pck_item = (LinearLayout)itemView.findViewById(R.id.LinerLayout_pck_item_Layout);
        }
    }
}
