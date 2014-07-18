package com.application.exception;

import android.content.Context;

public  class GeneralExceptionHandler {

	public static void handleException(Context mContext, Exception e) {
		boolean connectedToNetwork = true;//OSUtil.hasNetworkConnection(context);    	
//		if(connectedToNetwork){
//			if(e instanceof APDeviceNotRegisteredExcpetion){
//				if(!AppData.getBooleanProperty(APProperties.SILENT_UUID_FAILURE)){
//					handleDeviceNotRegisterdException(context);
//				}
//			}
//			else if(e instanceof APDeviceIdException){
//				handleDeviceIdException(context);
//			}
//			else{
//				Toaster.toast(context, context.getString(OSUtil.getStringResourceIdentifier("general_error")));
//			}
//		}
//		else{
//			Toaster.toast(context, context.getString(OSUtil.getStringResourceIdentifier("no_connection")));		
//		}		
	}

	
}
