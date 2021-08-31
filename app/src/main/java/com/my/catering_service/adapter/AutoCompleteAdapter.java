package com.my.catering_service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.catering_service.R;
import com.my.catering_service.model.CityMasterModel;

import java.util.ArrayList;

public class AutoCompleteAdapter extends BaseAdapter {

    private Context context;
    private int CityId[];
    private String[] CityName;
    private int mResource;
    private ArrayList<CityMasterModel> arrayList;
    private LayoutInflater layoutInflater;

//    public AutoCompleteAdapter(Context context, int[] cityId, String[] cityName, LayoutInflater layoutInflater) {
//        this.context = context;
//        this.CityId = cityId;
//        this.CityName = cityName;
//
//        this.layoutInflater = layoutInflater;
//    }

    public AutoCompleteAdapter(Context context, ArrayList<CityMasterModel> cityMasterModelArrayList) {
        this.context = context;
        this.arrayList = cityMasterModelArrayList;
        this.layoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.custom_spinner_items, null);
        TextView cityNames = (TextView) convertView.findViewById(R.id.txtCityName);
        CityMasterModel cityMasterModel = arrayList.get(position);
        cityNames.setText(cityMasterModel.getCityName());
        return convertView;

    }




}
