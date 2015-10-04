package com.application.listener;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.application.activities.BaseSplashActivity;
import com.application.app.ApplicationLifeycle;
import com.application.exception.GeneralExceptionHandler;
import com.application.interfaces.AccountLoadI;
import com.application.models.AccountModel;

public class AccountLoaderListener implements AsyncTaskListener<AccountModel> {

	private static final String TAG = "AccountLoaderListener";
	
	AccountLoadI listener;
	
	public AccountLoaderListener(BaseSplashActivity splashActivity) {
		listener = splashActivity;
	}

	@Override
	public void handleException(Exception e) {
		Log.e(TAG, "Error loading account, error = " + e.getMessage());
		GeneralExceptionHandler.handleException((Context)listener, e);
	}

	@Override
	public void onTaskComplete(AccountModel result) {
		boolean countinue = handleDeprecation(result);
		if(countinue) {
			listener.onAccountLoaded(result);
		}
	}

	private boolean handleDeprecation(AccountModel result) {
		boolean countinue = true;
		ApplicationLifeycle appLifecycle = new ApplicationLifeycle((Activity)listener,result);
		if(appLifecycle.isDeprecated()){
			appLifecycle.getDeprecationDialog().show();
			countinue = false;
		}
		return countinue;
	}

	@Override
	public void onTaskStart() {
		Log.d(TAG, "Start Load Account");
		
	}

	
}
