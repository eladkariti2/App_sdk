package com.application.facebook.model;


public class FbProfilePic extends FbModel{

	protected FBPicture picture;

	public String getUrl(){
		return picture.data.url;
	}

	protected  class FBPicture {
		protected FBPictureData data;
	}
	
	protected  class FBPictureData {
		protected String url;
	}
	
}
