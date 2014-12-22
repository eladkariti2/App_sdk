package com.application.database;

import java.util.ArrayList;
import java.util.List;

import com.application.models.APParkingModel;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DataBaseUtil {

	public static boolean saveUserLocationInDB(Context context,APParkingModel locationModel) {
		String longitude = locationModel.getLongitude() + "";
		String latitude = locationModel.getLatitude() + "";;
		String imagePath = locationModel.getImage();
		String address = locationModel.getAddress();
		String description = locationModel.getLocationDescription();	
		
		Log.d("DataBaseUtil","Insert to DB address = " + address + ", description = " + description + ", latitude = " + latitude + ", " +
				"longitude = " + longitude + ", image path = " + imagePath);
		
		APDataBaseHandler dbHandler = new APDataBaseHandler(context);
		dbHandler.open();
		long result = dbHandler.insert( description, address, latitude, longitude, imagePath, "");
		
		return result > 0 ;
	}

	public static 	List<APParkingModel> getAllLocations(Context context) {
		APDataBaseHandler dbHandler = new APDataBaseHandler(context);
		dbHandler.open();
		Cursor cursor = dbHandler.getAllLocations();
		
		List<APParkingModel> locations = new ArrayList<APParkingModel>();
		for(cursor.moveToNext(); !cursor.isAfterLast() ; cursor.moveToNext()){
			APParkingModel model = new APParkingModel();
			model.setID(cursor.getString(cursor.getColumnIndex(APDataBaseHandler.ID)));
			model.setAddress(cursor.getString(cursor.getColumnIndex(APDataBaseHandler.LOCATION_ADDRESS)));
			model.setLocationDescription(cursor.getString(cursor.getColumnIndex(APDataBaseHandler.LOCATION_NAME)));
			model.setLatitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(APDataBaseHandler.LOCATION_LATITUDE))));
			model.setLongitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(APDataBaseHandler.LOCATION_LONGITUDE))));
			model.setImage(cursor.getString(cursor.getColumnIndex(APDataBaseHandler.IMAGE_URL)));
			
			locations.add(model);
		}
		
		
		dbHandler.close();
		return locations;
		
	}

	public static boolean deleteLocation(Context context, String id) {
		APDataBaseHandler dbHandler = new APDataBaseHandler(context);
		dbHandler.open();
		Boolean  isDeleted = dbHandler.delete(id);
	
		if (isDeleted){
			Log.d("DataBaseUtil", "Location deleted, ID = " + id);
		}else{
			Log.d("DataBaseUtil", "Location not deleted, ID = " + id);
		}
			
		dbHandler.close();
		
		return isDeleted;
	}
}
