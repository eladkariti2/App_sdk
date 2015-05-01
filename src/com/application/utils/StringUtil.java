package com.application.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.ocpsoft.prettytime.PrettyTime;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.application.CustomApplication;
import com.google.android.maps.GeoPoint;

public class StringUtil {

	public static final DateFormat usDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
	public static final DateFormat programDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
	public static final  SimpleDateFormat internetDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	
	
	public static boolean isEmpty(String str) {
		boolean result = false;
		if (str == null || str.length() == 0) {
			result = true;
		}
		return result;
	}

	public static String parseHtml(String html) {
		String str = null;

		str = android.text.Html.fromHtml(html).toString();

		return str;
	}

	public static String ConvertPointToLocation(Context context,GeoPoint point) {
		String address = "";
		String country = "";
		Geocoder geoCoder = new Geocoder(context,
				Locale.getDefault());
		Log.i("StringUtil", " Locale.getDefault(): " +  Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocation(
					point.getLatitudeE6() / 1E6,
					point.getLongitudeE6() / 1E6, 1);
			if (addresses.size() > 0) {
				for (int index = 0; index < addresses.get(0).getMaxAddressLineIndex(); index++){
					address += addresses.get(0).getAddressLine(index) + " ";
				}
				country = addresses.get(0).getCountryName();

				Log.i("StringUtil", "address: " + address + ", country: " + country);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = address  + ", " + country;
		return result;
	}

	public static String getFBExpirationDate(long time){
		String dateStr = usDF.format(new Date(time));
		Log.d("StringUtil","FBExpirationDate:: " + dateStr);
		return dateStr;
	}

	public static String getRelationalDateString(Date date){
		Locale locale ;
		locale = CustomApplication.getApplicationLocale();
		
		PrettyTime p = new PrettyTime(locale);
		return p.format(date);		
	}

	
	//Adds the offset to date, the time zone +00 and need to change it according to the current time zone 
	private static Date addOffsetToDate(Date date) {
		// TODO Auto-generated method stub
		Calendar mCalendar = Calendar.getInstance(); 
		TimeZone mTimeZone = mCalendar.getTimeZone();  
		int mGMTOffset = mTimeZone.getRawOffset();
		Date result = new Date(date.getTime() + TimeUnit.MILLISECONDS.convert(mGMTOffset, TimeUnit.MILLISECONDS));
		return result;
	}

	public static String parseDateToFbDataFormat(Date date){
		String result = null;
		result = internetDF.format(date);
		return result;
	}

	public static Date parseDateToFbDataFormat(String dateStr) {
		// TODO Auto-generated method stub
		Date result =  null;
		try {
			result = internetDF.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
