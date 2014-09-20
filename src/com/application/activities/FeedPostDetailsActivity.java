package com.application.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.application.base.BaseActivity;
import com.application.imageholders.FBPostHolder;
import com.application.utils.JsonUtil;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;

public class FeedPostDetailsActivity extends BaseActivity {

	private final static String POST_HOLDER = "postHolder";
	
	FBPostHolder holder ;
	EditText mPostText;
	ListView mCommentsList ;
	ImageView mNoCommentImg ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(OSUtil.getLayoutResourceIdentifier("feed_post_detials_layout"));
		
		String holderJson = getIntent().getExtras().getString(POST_HOLDER);
		holder = (FBPostHolder)JsonUtil.serialize(holderJson, FBPostHolder.class);
		
		TextView likesNumber =  (TextView)findViewById(OSUtil.getResourceId("likes_number"));
		mPostText =  (EditText)findViewById(OSUtil.getResourceId("comment_text"));
		mCommentsList =  (ListView)findViewById(OSUtil.getResourceId("comments_list"));
		mNoCommentImg  = (ImageView)findViewById(OSUtil.getResourceId("no_comments_img"));
		
		String likes =  StringUtil.isEmpty(holder.getLikesNumber() + "") ?  "0" : "" + holder.getLikesNumber() ;
		likesNumber.setText(likes);
		
		//check if there is comments
		if(true){
			
			mNoCommentImg.setVisibility(View.GONE);
			mCommentsList.setVisibility(View.VISIBLE);
		}else{
			mNoCommentImg.setVisibility(View.VISIBLE);
			mCommentsList.setVisibility(View.GONE);
		}
	}
	
	
	
	public static void startFeedPostDetailsActivity(Context context,FBPostHolder holder){
		String json = JsonUtil.deserialize(holder, FBPostHolder.class);
		Intent intent = new Intent(context,FeedPostDetailsActivity.class);

		intent.putExtra(POST_HOLDER, json);
		
		context.startActivity(intent);
	}
}
