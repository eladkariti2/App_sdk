package com.application.listener;

import android.content.Context;
import android.util.Log;

import com.application.activities.BaseSplashActivity;
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
		listener.onAccountLoaded(result);
	}

	@Override
	public void onTaskStart() {
		Log.d(TAG, "Start Load Account");
		
	}

	
}
