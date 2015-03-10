package com.application.exception;

import org.apache.http.client.HttpResponseException;

import android.util.Log;


public class APException extends Exception {
	
	public static String TAG;
	public String type;
	Throwable rootCause;
	
	public APException(Throwable e){
		super(e);
		rootCause = e;
		TAG = this.getClass().getSimpleName();
		if(e != null){
			type =  e.getClass().getSimpleName(); 
			Log.e(TAG,"Exception " + e.getClass().getSimpleName() + " " + e.getMessage());
		}
		else{
			Log.e(TAG,"Exception " + null);
		}
	}
	
	public Throwable getRootCause(){
		return rootCause;
	}
	
	
	public static class APConnectionException extends APException {

		public static final int TIMEOUT_CODE = 999;
		int statusCode = -1;
		
		
		public APConnectionException(Throwable e) {
			super(e);
			if(e instanceof HttpResponseException){
				statusCode = ((HttpResponseException)e).getStatusCode();
			}
		}
		
		public int getStatusCode(){
			return statusCode;
		}

	}
	
	public static class APJsonParseException extends APException {

		public APJsonParseException(Throwable e) {
			super(e);
		}

	}
	
	public static class APTwitterException extends APException {

		public APTwitterException(Throwable e) {
			super(e);
		}
		
	}

}