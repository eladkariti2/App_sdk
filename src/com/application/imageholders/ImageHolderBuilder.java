package com.application.imageholders;

import java.util.ArrayList;
import java.util.List;

import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.text.APConstant;
import com.application.utils.AppData;
import com.application.utils.JsonUtil;

public class ImageHolderBuilder {

	public final static  String ME_LIKED_POST = "me_liked_post";
	
	public static ArrayList<ImageHolder> createFBPostHolder( List<FBPost> posts){
		ArrayList<ImageHolder> holders = new ArrayList<ImageHolder>();
		String user =  AppData.getProperty(APConstant.USER_FACEBOOK_PROFILE);
		FBModel model = (FBModel)JsonUtil.serialize(user, FBModel.class);
		for(FBPost p : posts){
			FBPostHolder holder = new FBPostHolder(p.getId(),p.getUserName(),p.getCreatedTime(), p.getUserPicture(),p.getMessage(),p.getPostPicture(),p.getCaption(),p.getLikeNumber(),p.getComments().size());
			holders.add(holder);
			checkIfLiked(holder,p,model.getId());
		}
		
		return holders;
	}

	private static void checkIfLiked(FBPostHolder holder, FBPost p,String userID) {
		if(p.getFBModelLikes() != null ){
			for(FBModel model : p.getFBModelLikes()){
				if(model.getId().equals(userID) )
				{
					holder.addExtension(ME_LIKED_POST, "true"  );
					p.setIsUserLike(true);
				}
			}
		}
	}

	public static List<ImageHolder> createPostCommentsHolders(	List<FBPost> comments) {
		// TODO Auto-generated method stub
		List<ImageHolder> holders = new ArrayList<ImageHolder>();
		if(comments != null)
			for(FBPost comment : comments){
				FBPostHolder holder = new FBPostHolder(comment.getId(),comment.getUserName(),comment.getCreatedTime(), comment.getUserPicture(),comment.getMessage(),null,comment.getCaption(),0,0);
				holders.add(holder);
			}
		
		return holders;
	}
	
	
}
