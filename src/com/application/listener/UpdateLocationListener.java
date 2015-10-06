package com.application.listener;

import android.content.Context;
import android.util.Log;

import com.application.models.GenericModel;

/**
 * Created by elad on 10/5/2015.
 */
public class UpdateLocationListener extends  GeneralModelListener {
    private static final String TAG = "updateLocationListener";

    String mLatitude;
    String mLongitude;

    public  UpdateLocationListener(Context context,String latitude,String longitude){
        super(context);
        mLongitude = longitude;
        mLatitude = latitude;
    }

    @Override
    public void onTaskStart() {
        Log.i(TAG, "start update location: latitude : " + mLatitude + ", longitude : " + mLongitude);
    }

    @Override
    public void onTaskComplete(GenericModel result) {
        Log.i(TAG, "finsh successfully to update location: latitude : " + mLatitude + ", longitude : " + mLongitude);
    }
}
