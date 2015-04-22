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
import com.application.datahandler.FBEventDataHandler;
import com.application.facebook.loader.APCommentsRequest;
import com.application.facebook.loader.APPostCommentRequest;
import com.application.facebook.model.FBComment;
import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.facebook.util.FacebookUtil;
import com.application.imageholders.FBPostHolder;
import com.application.imageholders.ImageHolder;
import com.application.imageholders.ImageHolderBuilder;
import com.application.listener.AsyncTaskListener;
import com.application.utils.JsonUtil;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;

public class FeedPostDetailsActivity extends BaseActivity {

	private final static String POST_HOLDER = "postHolder";

	protected static final String TAG = "FeedPostDetailsActivity";

	FBPostHolder holder ;
	EditText mPostText;
	ListView mCommentsList ;
	View mNoCommentsContainer ;
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
		 mNoCommentsContainer = findViewById(OSUtil.getResourceId("no_comments_container"));
		 
		View postButton  = findViewById(OSUtil.getResourceId("post_text_container"));

		String likes =  StringUtil.isEmpty(holder.getLikesNumber() + "") ?  "0" : "" + holder.getLikesNumber() ;
		likesNumber.setText(likes);
		
		loadCommentIfNeeded();

		postButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPostTextMessage = mPostText.getText().toString();
				postComment();
			}
		});
	}


	private void postComment() {
		// TODO Auto-generated method stub
		APPostCommentRequest req = new APPostCommentRequest(holder.getID(), mPostTextMessage, new AsyncTaskListener<FBModel>() {
			
			@Override
			public void onTaskStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTaskComplete(FBModel result) {
				// TODO Auto-generated method stub
				loadComment();
				mPostTextMessage = "";
				mPostText.setText("");
			}
			
			@Override
			public void handleException(Exception e) {
				// TODO Auto-generated method stub
				
			}
		});
		req.doQuery();
	}

	private void loadCommentIfNeeded() {
		// TODO Auto-generated method stub
		if(holder.getCommentNumber() > 0){
			loadComment();
		}else{
			displayNoComments();
		}
	}



	private void loadComment() {
		// TODO Auto-generated method stub
		String id = holder.getID();
		APCommentsRequest req = new APCommentsRequest(id, new AsyncTaskListener<FBModel>() {
			
			@Override
			public void onTaskStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTaskComplete(FBModel result) {
				// TODO Auto-generated method stub
				updateList((FBPost)result);
			}
			
			@Override
			public void handleException(Exception e) {
				// TODO Auto-generated method stub
				
			}
		});
		req.doQuery();
	}



	private void displayNoComments() {
		// TODO Auto-generated method stub
		mNoCommentsContainer.setVisibility(View.VISIBLE);
		mCommentsList.setVisibility(View.GONE);
	}



	private void updateList(FBPost post) {
		Log.e("ELAD", "FeedPoistDetialse updateList");
		// TODO Auto-generated method stub
		List<FBComment> comments = post.getComments();
		mCommentsHolders = ImageHolderBuilder.createPostCommentsHolders(comments);

		//check if there is comments
		if(mCommentsHolders.size() >0){

			Mapper mapper = new Mapper("feed_comment_details", OSUtil.getResourceId("avatar"));
			CommentsFeedAdapter adapter = new CommentsFeedAdapter(this, (ArrayList<ImageHolder>) mCommentsHolders, mapper);
			mCommentsList.setAdapter(adapter);

			mNoCommentsContainer.setVisibility(View.GONE);
			mCommentsList.setVisibility(View.VISIBLE);
		}else{
			displayNoComments();
		}
	}



	public static void startFeedPostDetailsActivity(Context context,FBPostHolder holder){
		String json = JsonUtil.deserialize(holder, FBPostHolder.class);
		Intent intent = new Intent(context,FeedPostDetailsActivity.class);

		intent.putExtra(POST_HOLDER, json);

		context.startActivity(intent);
	}
}
