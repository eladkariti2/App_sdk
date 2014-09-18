package com.application.picasoimageloader;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.application.utils.OSUtil;
import com.application.utils.StringUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicasoHalper {
	public static final String TAG = "PicasoHalper";
	
	
	public static void loadImage(Context context,ImageView image,String imagePeth){
		loadImage(context,image,imagePeth,"");
	}
	
	public  static void loadImage(Context context,ImageView image,String imagePeth,String placeHolder){
		
		if(StringUtil.isEmpty(imagePeth)){
			Log.e(TAG, "Image path is null");
			return;
		}
		
		Picasso.Builder builder = new Picasso.Builder(context);
	    builder.listener(new Picasso.Listener() {

			@Override
			public void onImageLoadFailed(Picasso pic, Uri uri,
					Exception e) {
				Log.e(TAG, "Error loading Image, url = " + uri.getPath() + "exception = " + e.getMessage());
				
			}
	    });
	    Picasso pic = builder.build();
	    if(StringUtil.isEmpty(placeHolder)){
	    	pic.load(imagePeth).into(image);
	    }else{
	    	pic.load(imagePeth).placeholder(OSUtil.getDrawableResourceIdentifier(placeHolder)).into(image);
	    }
	}
	
	
}
