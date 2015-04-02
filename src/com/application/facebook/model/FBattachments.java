package com.application.facebook.model;

import java.util.List;

import com.google.android.gms.internal.ei;

public class FBattachments {

	public List<FBattachmentsData> data;
	
	public int getWidth()
	{
		int width = 0 ;
		if(data!= null && data.get(0) != null && data.get(0).media != null && data.get(0).media.image != null){
			width = data.get(0).media.image.width;
		}
		return width;
	}
	
	public int getHeight()
	{
		int height = 0 ;
		if(data!= null && data.get(0) != null && data.get(0).media != null && data.get(0).media.image != null){
			height = data.get(0).media.image.height;
		}
		return height;
	}
	
	public class FBattachmentsData {
		public FBattachmentsDataMedia media;
	}
	
	public class FBattachmentsDataMedia {
		public FBattachmentsDataMediaImage image;
	}
	
	public class FBattachmentsDataMediaImage {
		public int height;
		public int width;
	}
}
