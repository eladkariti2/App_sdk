package com.application.gps.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.application.messagebroker.APBrokerNotificationTypes;
import com.application.messagebroker.APMessageBroker;

public class APLocationListenr implements LocationListener{

	@Override
	public void onLocationChanged(Location location) {
			
		APMessageBroker.getInstance().fireNotificationsByType(APBrokerNotificationTypes.AP_BROKER_UPDATE_LOCATION,location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}