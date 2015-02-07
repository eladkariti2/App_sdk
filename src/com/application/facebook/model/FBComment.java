package com.application.facebook.model;


public class FBComment extends FBPost {
	
	protected FBAttachment attachment ;
	
	public String getAttachmentImage(){
		String result = "";
		if (attachment != null)
		{
			result = attachment.getImage();
		}
		return result;
	}
	
	public String getAttachmentType(){
		String result = "";
		if (attachment != null)
		{
			result = attachment.getType();
		}
		return result;
	}

}