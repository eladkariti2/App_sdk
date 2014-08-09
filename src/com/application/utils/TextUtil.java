package com.application.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextUtil {

	
	public static void setTextFont(Context context, TextView textview,AttributeSet attrs){
		TypedArray styledAttrs = context.obtainStyledAttributes(attrs,new int[]{OSUtil.getAttributeResourceIdentifier("customtypeface")},0,0);
		int index = styledAttrs.getInt(0, -1);
		setTypeFace(context,TypeFaceStyle.fromInteger(index),textview);
		styledAttrs.recycle();
	}

	
	public static void setTypeFace(Context context,TypeFaceStyle style,TextView view) {
		String fontPath = null;
		switch (style) {
		case REGULAR:
			fontPath = context.getString(OSUtil.getStringResourceIdentifier("font_name_regular"));
			break;
		case LIGHT:
			fontPath = context.getString(OSUtil.getStringResourceIdentifier("font_name_light"));
			break;
		case EXTRA_LIGHT:
			fontPath = context.getString(OSUtil.getStringResourceIdentifier("font_name_extra_light"));
			break;
		case BOLD:
			fontPath = context.getString(OSUtil.getStringResourceIdentifier("font_name_bold"));
			break;
		case EXTRA_BOLD:
			fontPath = context.getString(OSUtil.getStringResourceIdentifier("font_name_extra_bold"));
			break;
		default:
			break;
		}
		
		if(!StringUtil.isEmpty(fontPath)){
			fontPath = "fonts/" + fontPath;	
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
			view.setTypeface(typeface);
		}

	}


	public enum TypeFaceStyle {
		REGULAR,LIGHT,EXTRA_LIGHT,BOLD,EXTRA_BOLD,DEFAULT;

		public static TypeFaceStyle fromInteger(int index) {
			TypeFaceStyle result = DEFAULT;
			switch(index) {
				case 0:{
					result =  REGULAR;
					break;
				}
				case 1:
				{
					result =  LIGHT;
					break;
				}
				case 2:
				{
					result =  EXTRA_LIGHT;
					break;
				}
				case 3:
				{
					result =  BOLD;
					break;
				}
				case 4:
				{
					result =  EXTRA_BOLD;
					break;
				}
				case -1:
				{
					result =  DEFAULT;
					break;
				}
			}		
			return result;
		}
	}
}
