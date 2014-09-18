package com.application.activities;


import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.application.base.BaseActivity;
import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.listener.FacebookLoaderListener;
import com.application.facebook.model.FbModel;
import com.application.facebook.util.FacebookUtil;
import com.application.text.APConstant;
import com.application.utils.AndroidBug5497Workaround;
import com.application.utils.OSUtil;

public class FeedPostActivity extends BaseActivity {
	
	protected static final String TAG = "FeedPostActivity";

	private static String ADD_PHOTO = "add_photo";
	
	View postButton;
	EditText postText;
	View postActionView;
	View takePick;
	View addPick;
	ImageView mPostImage;
	Bitmap imageToPost;
	String messageToPost;
	ProgressBar mProgressBar;
	FacebookLoaderListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(OSUtil.getLayoutResourceIdentifier("feed_post_layout"));
		postText = (EditText)findViewById(OSUtil.getResourceId("post_text"));
		postButton = findViewById(OSUtil.getResourceId("post_button"));
		postActionView = findViewById(OSUtil.getResourceId("post_layout_container"));
		addPick = findViewById(OSUtil.getResourceId("add_pick"));
		takePick = findViewById(OSUtil.getResourceId("take_pick"));
		mPostImage = (ImageView)findViewById(OSUtil.getResourceId("post_image"));
		mProgressBar = (ProgressBar)findViewById(OSUtil.getResourceId("post_progress_bar"));
		
		addPick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dispatchTakePictureIntent(false);
			}
		});
	
		
    	takePick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dispatchTakePictureIntent(true);
			}
		});
		
		postButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				postToFacebook(messageToPost,imageToPost);
			}

			
		});
		listener = new FacebookLoaderListener(this,new FacebookLoaderI() {
			
			@Override
			public void onSuccess(FbModel model) {
				// TODO Auto-generated method stub
				
				FeedPostActivity.this.finish();
			}
			
			@Override
			public void onFailure(Exception e) {
				// TODO Auto-generated method stub
				Log.e(TAG, e.getMessage());
				mProgressBar.setVisibility(View.GONE);
			}
		});
		AndroidBug5497Workaround.assistActivity(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	//	
	}

	private void postToFacebook(String message,Bitmap image) {
		mProgressBar.setVisibility(View.VISIBLE);
		FacebookUtil.postFeedTofacebook(this,listener,message,image);
	}
	
	
	private void dispatchTakePictureIntent(boolean fromCamera) {
		if(fromCamera){
			OSUtil.launchCamera(this);
		}else{
			OSUtil.launchGallery(this);
		}
	}
	
	//Handle the picture result.
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if ( resultCode == RESULT_OK) {
				Log.d(TAG, "Captuer pictuer status OK");
				if(requestCode == APConstant.REQUEST_IMAGE_CAPTURE_CAMERA){
					Bundle extras = data.getExtras();
					imageToPost = (Bitmap) extras.get("data");
				}else if (requestCode == APConstant.REQUEST_IMAGE_CAPTURE_GALLERY){
					Uri imageUri = data.getData();
					try {
						imageToPost = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
				
				mPostImage.setImageBitmap(imageToPost);
				mPostImage.setVisibility(View.VISIBLE);
				
			}
			else{
				Log.e(TAG, "Failed to captuer image");
			}
		}
	
	
	public static void startFeedPostActivity(Context context,boolean withPhoto){
		Intent i = new Intent (context,FeedPostActivity.class);
		i.putExtra(ADD_PHOTO, withPhoto);
		
		context.startActivity(i);
	}

}
