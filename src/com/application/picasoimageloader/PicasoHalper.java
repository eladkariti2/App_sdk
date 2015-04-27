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
	
	
	public synchronized static  void loadImage(Context context,ImageView image,String imagePeth){
		loadImage(context,image,imagePeth,"");
	}
	
	public  synchronized static void loadImage(Context context,ImageView image,String imagePeth,String placeHolder){
		
		if(StringUtil.isEmpty(imagePeth)){
			Log.e(TAG, "Image path is null");
			return;
		}
	    if(StringUtil.isEmpty(placeHolder)){
	    	Picasso.with(context).load(imagePeth).into(image);
	    }else{
	    	Picasso.with(context).load(imagePeth).placeholder(OSUtil.getDrawableResourceIdentifier(placeHolder)).into(image);
	    }
	}
	
	
}
