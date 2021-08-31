package com.my.catering_service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.catering_service.R;
import com.my.catering_service.model.CartMasterModel;
import com.my.catering_service.model.CartMasterResultModel;
import com.my.catering_service.model.CartMasterViewModel;

import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {
    Context context;
    List<CartMasterViewModel> cartMasterModel;

    public CartViewAdapter(List<CartMasterViewModel> cartMasterModel, Context context) {
        this.context = context;
        this.cartMasterModel = cartMasterModel;
    }

    @Override
    public CartViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_view_layout,parent,false);
        return new CartViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_cartmastertitle.setText(cartMasterModel.get(position).getSpcmName());
        holder.txt_cartmasterPrice.setText(String.valueOf(cartMasterModel.get(position).getPackagePrice()));
        holder.txtcartmasterDate.setText(cartMasterModel.get(position).getC_Date());
        holder.txt_cartmasterMember.setText(String.valueOf(cartMasterModel.get(position).getNoOfMember()));

    }

    @Override
    public int getItemCount() {
        return  cartMasterModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_cartmastertitle,txtcartmasterDate,txt_cartmasterPrice,txt_cartmasterMember;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_cartmastertitle = (TextView)itemView.findViewById(R.id.txt_Cart_Master_title);
            txt_cartmasterPrice = (TextView)itemView.findViewById(R.id.txt_Cart_Master_Price);
            txtcartmasterDate = (TextView)itemView.findViewById(R.id.txt_Cart_Master_Date);
            txt_cartmasterMember = (TextView)itemView.findViewById(R.id.txt_Cart_Master_Member);
        }
    }
}
