package com.application.facebook.listener;

import android.content.Context;
import android.widget.ImageView;

import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.model.FbModel;
import com.application.imageholders.FBPostHolder;
import com.application.imageholders.ImageHolderBuilder;
import com.application.utils.OSUtil;

public class LikeClickListener implements FacebookLoaderI {

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
	
	@Override
	public void onSuccess(FbModel model) {
		// TODO Auto-generated method stub
		mIsliked = !mIsliked;
		mHolder.addExtension(ImageHolderBuilder.ME_LIKED_POST,mIsliked + "" );
		mImage.setImageResource(mIsliked ? OSUtil.getDrawableResourceIdentifier("facebook_entry_icon_active") : OSUtil.getDrawableResourceIdentifier("facebook_entry_like_icon") );
	}

	@Override
	public void onFailure(Exception e) {
		// TODO Auto-generated method stub
		
	}

}
