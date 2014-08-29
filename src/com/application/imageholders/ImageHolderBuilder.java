package com.application.imageholders;

import java.util.ArrayList;
import java.util.List;

import com.application.facebook.model.FBPost;

public class ImageHolderBuilder {

	
	public static ArrayList<ImageHolder> createFBPostHolder( List<FBPost> posts){
		ArrayList<ImageHolder> holders = new ArrayList<ImageHolder>();
		for(FBPost p : posts){
			FBPostHolder holder = new FBPostHolder(p.getId(),p.getUserName(),p.getCreatedTime(), p.getUserPicture(),p.getMessage(),p.getPostPicture(),p.getCaption(),1,2);
			holders.add(holder);
		}
		
		return holders;
	}
	
	
}
