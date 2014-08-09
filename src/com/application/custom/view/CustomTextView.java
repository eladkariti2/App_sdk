package com.application.custom.view;

import com.application.utils.TextUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class CustomTextView extends TextView {

	
	public CustomTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TextUtil.setTextFont(context, this, attrs);
	}
	
}