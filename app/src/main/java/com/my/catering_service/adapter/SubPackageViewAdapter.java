package com.my.catering_service.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.catering_service.R;
import com.my.catering_service.activity.PackageManuActivity;
import com.my.catering_service.model.SubPackageMasterModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubPackageViewAdapter extends RecyclerView.Adapter<SubPackageViewAdapter.ViewHolder> {
    List<SubPackageMasterModel> subPackageMasterModelslist;
    Context context;

    public SubPackageViewAdapter(List<SubPackageMasterModel> subPackageMasterModelslist, Context context) {
        this.subPackageMasterModelslist = subPackageMasterModelslist;
        this.context = context;
    }

    @NonNull
    @Override
    public SubPackageViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_sub_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubPackageViewAdapter.ViewHolder holder, int position) {
        final SubPackageMasterModel item = subPackageMasterModelslist.get(position);
        StringBuilder price = new StringBuilder();
        StringBuilder member = new StringBuilder();

        holder.subpackage_title.setText(item.getSpcmName());
        //For Price
        price.append("Rs.").append(String.valueOf(item.getSpcmPrice())).append("/").append("member");
        holder.subpackage_price.setText(price);
        //For Member
        member.append("(Min member ").append(item.getMiniMember()).append(" )");
        holder.subpackage_member.setText(member);

        holder.subpackage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PackageManuActivity.class);
                intent.putExtra("Id",String.valueOf(item.getSpcmid()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subPackageMasterModelslist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView subpackage_title,subpackage_price,subpackage_member;
        private RelativeLayout subpackage_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subpackage_title = (TextView)itemView.findViewById(R.id.txt_subpackage_title);
            subpackage_price = (TextView)itemView.findViewById(R.id.txt_subpackage_price);
            subpackage_member = (TextView)itemView.findViewById(R.id.txt_subpackage_members);
            subpackage_btn = (RelativeLayout) itemView.findViewById(R.id.txt_subpackage_btn);
        }
    }
}
