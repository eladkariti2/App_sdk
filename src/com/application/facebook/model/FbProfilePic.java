package com.application.facebook.model;

public class FBProfilePic extends FBModel {

	protected FBPicture picture;

	public String getUrl(){
		return picture.data.url;
	}
	
	public String setUrl(String url){
		return picture.data.url = url;
	}

	protected  class FBPicture {
		protected FBPictureData data;
	}
	
	protected  class FBPictureData {
		protected String url;
	}
}
