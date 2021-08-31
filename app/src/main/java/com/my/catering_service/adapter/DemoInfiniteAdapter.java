package com.my.catering_service.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.my.catering_service.R;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;

public class DemoInfiniteAdapter extends LoopingPagerAdapter<Integer> {
    View imageview;
    public DemoInfiniteAdapter(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View getItemView(View view, int i, ViewPager viewPager) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_looping_view_layout, null);
            imageview = view.findViewById(R.id.loopingImage);
            viewPager.addView(view);
        }
        imageview.setBackgroundResource(getBackgroundColor(i));
        //view.findViewById(R.id.loopingImage).setBackgroundColor(context.getResources().getColor(getBackgroundColor(i)));
        //TextView description = view.findViewById(R.id.description);
        //description.setText(String.valueOf(itemList.get(listPosition)));
        //view.findViewById(R.id.loopingImage);

        return view;
    }
    private int getBackgroundColor (int number) {
        switch (number) {
            case 0:
                return R.drawable.loopingone;
            case 1:
                return R.drawable.loopingtwo;
            case 2:
                return R.drawable.loopingthree;

            default:
                return R.drawable.loopingone;
        }
    }
}
