package com.application.utils;

import com.google.android.maps.GeoPoint;

import android.content.Context;

public class MapUtil {

	public static String getAddrres(Context context, double latitud,double longitude) {
		int lat = (int) (latitud * 1E6);
		int lng = (int) (longitude * 1E6);
		GeoPoint point = new GeoPoint(lat,lng);	
		String addressText = StringUtil.ConvertPointToLocation(context, point);	
		return addressText;
	}
	

}
