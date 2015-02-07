package com.application.facebook.listener;

import android.content.Context;
import android.widget.ImageView;

import com.application.facebook.model.FBModel;
import com.application.imageholders.FBPostHolder;
import com.application.imageholders.ImageHolderBuilder;
import com.application.utils.OSUtil;

public class LikeClickListener  {

	Context mContext;
	FBPostHolder mHolder;
	boolean mIsliked ;
	ImageView mImage;
	
	public LikeClickListener(Context context,FBPostHolder holder,ImageView image){
		this(context,holder,false,image);
	}
	
	public LikeClickListener(Context context,FBPostHolder holder,boolean isLiked,ImageView image){
		mContext = context;
		mImage = image;
		mHolder = holder;
		mIsliked = isLiked;
	}
	
	public void onSuccess(FBModel model) {
		// TODO Auto-generated method stub
		mIsliked = !mIsliked;
		mHolder.addExtension(ImageHolderBuilder.ME_LIKED_POST,mIsliked + "" );
		mImage.setImageResource(mIsliked ? OSUtil.getDrawableResourceIdentifier("facebook_entry_icon_active") : OSUtil.getDrawableResourceIdentifier("facebook_entry_like_icon") );
	}

	public void onFailure(Exception e) {
		// TODO Auto-generated method stub
		
	}

}
