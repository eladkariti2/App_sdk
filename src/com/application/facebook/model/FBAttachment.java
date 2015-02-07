package com.application.facebook.model;

public class FBAttachment  {

	protected String type;
	protected AttachmentMedia media;
	
	public String getType(){
		return type;
	}
	
	
	public String getImage(){
		String result = "";
		if (media != null && media.image != null)
		{
			result = media.image.src;
		}
		return result;
	}
	

	
}