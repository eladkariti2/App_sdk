package com.application.facebook.model;


public class FBProfilePic extends FBModel {

	protected FBPicture picture;
	protected String birthday;
	
	public String getUrl(){
		return picture.getFBPictureData().getUrl();
	}
	
	public void setUrl(String url){
		picture.getFBPictureData().setUrl(url);
	}
	
	public String gebirthday(){
		return birthday;
	}
	
	public void setbirthday(String birthday){
		this.birthday = birthday;
	}
}