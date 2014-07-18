package com.application.listener;

import android.content.Context;

import com.application.exception.GeneralExceptionHandler;
import com.application.models.GenericModel;

public abstract class GeneralModelListener implements AsyncTaskListener<GenericModel> {

	protected Context mContext;
	
	public GeneralModelListener(Context context){
		super();
		mContext = context;		
	}
	
	/**
	 * By default will show a Connection/Loading error message
	 */
	@Override
	public void handleException(Exception e) {
		GeneralExceptionHandler.handleException(mContext, e);		
	}

	/**
	 * Placeholder for concrete implementation to extract the specific APModel and to load the successor Loader if 
	 * exists
	 */
	@Override
	public abstract void onTaskComplete(GenericModel result);

	@Override
	public void onTaskStart() {
		// TODO Auto-generated method stub
		
	}
}
