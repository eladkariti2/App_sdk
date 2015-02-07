package com.application.activities;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.application.adapters.CommentsFeedAdapter;
import com.application.adapters.ImageBaseAdapter.Mapper;
import com.application.base.BaseActivity;
import com.application.facebook.model.FBPost;
import com.application.facebook.util.FacebookUtil;
import com.application.facebook.util.FeedLoadingManger;
import com.application.imageholders.FBPostHolder;
import com.application.imageholders.ImageHolder;
import com.application.imageholders.ImageHolderBuilder;
import com.application.utils.JsonUtil;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;

public class FeedPostDetailsActivity extends BaseActivity {

	private final static String POST_HOLDER = "postHolder";

	protected static final String TAG = "FeedPostDetailsActivity";

	FBPostHolder holder ;
	EditText mPostText;
	ListView mCommentsList ;
	ImageView mNoCommentImg ;
	List<ImageHolder> mCommentsHolders;
	String mPostTextMessage ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(OSUtil.getLayoutResourceIdentifier("feed_post_detials_layout"));

		String holderJson = getIntent().getExtras().getString(POST_HOLDER);
		holder = (FBPostHolder)JsonUtil.serialize(holderJson, FBPostHolder.class);

		TextView likesNumber =  (TextView)findViewById(OSUtil.getResourceId("likes_number"));
		mPostText =  (EditText)findViewById(OSUtil.getResourceId("comment_text"));
		mCommentsList =  (ListView)findViewById(OSUtil.getResourceId("comments_list"));
		mNoCommentImg  = (ImageView)findViewById(OSUtil.getResourceId("no_comments_img"));
		View postButton  = findViewById(OSUtil.getResourceId("post_text_container"));

		String likes =  StringUtil.isEmpty(holder.getLikesNumber() + "") ?  "0" : "" + holder.getLikesNumber() ;
		likesNumber.setText(likes);

		updateList();
		
//		final FacebookLoaderListener listener = new FacebookLoaderListener(this,new FacebookLoaderI() {
//
//			@Override
//			public void onSuccess(FbModel model) {
//				// TODO Auto-generated method stub
//
//				FbProfilePic userProfile =  FacebookUtil.getUserProfile();
//				
//				
//				//Create comment object to add
//				FBPost comment = new FBPost();
//				comment.setId(model.getId());
//				comment.setMessage(mPostTextMessage );
//				comment.setUserProfile(userProfile);
//				
//				Calendar c = Calendar.getInstance(); 
//				Date date =  c.getTime();
//				comment.setCreatedTime(StringUtil.usDF.format(date));
//				
//				//update post comment list with the new comment 
//				FeedLoadingManger.getInstance().addCommentToPost(holder.getID(), comment);
//				updateList();
//			}
//
//			@Override
//			public void onFailure(Exception e) {
//				// TODO Auto-generated method stub
//				Log.e(TAG, e.getMessage() + "");
//			}
//		});

		postButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPostTextMessage = mPostText.getText().toString();
				//FacebookUtil.postCommentTofeed(FeedPostDetailsActivity.this,listener,holder.getID(),mPostTextMessage );
			}


		});
	}



	private void updateList() {
		Log.e("ELAD", "FeedPoistDetialse updateList");
		// TODO Auto-generated method stub
		List<FBPost> comments = FeedLoadingManger.getInstance().getPostComments(holder.getID()) ;
		mCommentsHolders = ImageHolderBuilder.createPostCommentsHolders(comments);

		//check if there is comments
		if(mCommentsHolders.size() >0){

			Mapper mapper = new Mapper("feed_comment_details", OSUtil.getResourceId("avatar"));
			CommentsFeedAdapter adapter = new CommentsFeedAdapter(this, (ArrayList<ImageHolder>) mCommentsHolders, mapper);
			mCommentsList.setAdapter(adapter);

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
