package com.my.catering_service.util;

import android.app.Activity;
import android.content.Intent;

import com.my.catering_service.R;

public class Utils {
    public static void StartActivity(Activity activity, Class<?> cls){
        activity.startActivity(new Intent(activity,cls));
        activity.overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        activity.finish();
    }
}
