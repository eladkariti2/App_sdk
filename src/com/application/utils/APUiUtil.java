package com.application.utils;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class APUiUtil {

	public static void adjustImageToFullScreenWidth(final ImageView imageView, int picOriginWidth,int picOriginHeight){
		final float sizeFactor =(float)picOriginHeight / (float)picOriginWidth;
		imageView.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,(int) (imageView.getWidth() * sizeFactor));
						imageView.setLayoutParams(lp);
						imageView.setScaleType(ScaleType.CENTER_CROP);
						
					}
				});
	}
}
