package com.application.adapters;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.imageholders.FBPostHolder;
import com.application.imageholders.ImageHolder;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;

public class CommentsFeedAdapter extends ImageBaseAdapter{

	
	public CommentsFeedAdapter(Context context, ArrayList<ImageHolder> data,Mapper mapper) {
		super(context, data, mapper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = super.getView(position, convertView, parent);
		TextView userName = (TextView) view.findViewById(OSUtil.getResourceId("user_name"));
		TextView userPost = (TextView) view.findViewById(OSUtil.getResourceId("post_text"));
		TextView creationTimeTV = (TextView) view.findViewById(OSUtil.getResourceId("post_creation_time"));
		
		FBPostHolder holder = (FBPostHolder)getItem(position);
		
		userName.setText(holder.getUserName());
		userPost.setText(holder.getMessage());
		
		String dateStr = holder.getPostDate();
		String creationTime = "";

		if(!StringUtil.isEmpty(dateStr)){
			Date date  = StringUtil.parseDateToFbDataFormat(dateStr);
			creationTime = StringUtil.getRelationalDateString(date);
		}
		
		creationTimeTV.setText(creationTime);
		
		return view;
	}

}
