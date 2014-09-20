package com.application.imageholders;

import java.util.ArrayList;
import java.util.List;

import com.application.facebook.model.FBPost;
import com.application.facebook.model.FbModel;
import com.application.text.APConstant;
import com.application.utils.AppData;
import com.application.utils.JsonUtil;

public class ImageHolderBuilder {

	public final static  String ME_LIKED_POST = "me_liked_post";
	
	public static ArrayList<ImageHolder> createFBPostHolder( List<FBPost> posts){
		ArrayList<ImageHolder> holders = new ArrayList<ImageHolder>();
		String user =  AppData.getProperty(APConstant.USER_FACEBOOK_PROFILE);
		FbModel model = (FbModel)JsonUtil.serialize(user, FbModel.class);
		for(FBPost p : posts){
			FBPostHolder holder = new FBPostHolder(p.getId(),p.getUserName(),p.getCreatedTime(), p.getUserPicture(),p.getMessage(),p.getPostPicture(),p.getCaption(),1,2);
			holders.add(holder);
			checkIfLiked(holder,p,model.getId());
		}
		
		return holders;
	}

	private static void checkIfLiked(FBPostHolder holder, FBPost p,String userID) {
		if(p.getLike() != null ){
			for(FbModel model : p.getLike()){
				holder.addExtension(ME_LIKED_POST,model.getId().equals(userID) ? "true"  : "false");
			}
		}
	}
	
	
}
