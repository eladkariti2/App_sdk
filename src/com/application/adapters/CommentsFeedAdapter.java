package com.application.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.application.imageholders.ImageHolder;

public class CommentsFeedAdapter extends ImageBaseAdapter{

	
	public CommentsFeedAdapter(Context context, ArrayList<ImageHolder> data,
			Mapper mapper) {
		super(context, data, mapper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = getView(position, convertView, parent);
		
		
		return view;
	}

}
