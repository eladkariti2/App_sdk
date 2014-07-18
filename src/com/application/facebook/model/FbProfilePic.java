package com.application.facebook.model;


public class FbProfilePic extends FbModel{

	protected FBPictureData data;

	public String getUrl(){
		return data.url;
	}

	protected  class FBPictureData {
		protected String url;
	}
	
}
