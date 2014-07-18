package com.application.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.imageholders.ImageHolder;
import com.application.utils.OSUtil;

public class ImageBaseAdapter extends BaseAdapter {

	protected Context mContext;
	protected ArrayList<ImageHolder> mData ;
	protected Mapper mMapper;
	
	protected int mImageWidth, mImageHeight;
	String mPlaceHolder;
	
	protected LayoutInflater inflater;
	
	public ImageBaseAdapter(Context context,ArrayList<ImageHolder> data,Mapper mapper){
		this(context,data,mapper,-1,-1,"");
	}
	
	public ImageBaseAdapter(Context context,ArrayList<ImageHolder> data,Mapper mapper,int width
			,int height,String placeHolder){
		mContext = context;
		mData = data;
		mMapper = mapper;
		mPlaceHolder = placeHolder;
		mImageHeight = height;
		mImageWidth = width;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public ImageHolder getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageHolder holder = getItem(position);
		
		if(convertView == null){
			convertView = inflater.inflate(OSUtil.getLayoutResourceIdentifier(mMapper.itemLayoutName), parent, false);
		}
		
		
		return null;
	}
	
	
	
	
	public static class Mapper {
		public String itemLayoutName;
		public int imageViewId;
		
		public Mapper(String itemLayoutName, int imageViewId) {
			this.itemLayoutName = itemLayoutName;
			this.imageViewId = imageViewId;
		}
	}

}
