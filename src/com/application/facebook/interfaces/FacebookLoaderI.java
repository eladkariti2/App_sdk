package com.application.facebook.interfaces;

import com.application.facebook.model.FbModel;

public interface FacebookLoaderI {

	public void onSuccess(FbModel model);
	public void onFailure(Exception e);	
}
