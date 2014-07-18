package com.application.ui;

import com.application.utils.OSUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SplashLoader extends RelativeLayout {

	ImageView mProgressLoader;
	public SplashLoader(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mProgressLoader =(ImageView) findViewById(OSUtil.getResourceId("progress_indicator"));
	}

}
