package com.application.facebook.model;


public class FBProfilePic extends FBModel {

	protected FBPicture picture;

	public String getUrl(){
		return picture.getFBPictureData().getUrl();
	}
	
	public void setUrl(String url){
		picture.getFBPictureData().setUrl(url);
	}
}