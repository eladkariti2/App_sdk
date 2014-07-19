package com.application.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.application.imageholders.ImageHolder;
import com.application.picasoimageloader.PicasoHalper;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageBaseAdapter extends BaseAdapter {

	public static final String TAG = "ImageBaseAdapter";
	
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
		
		ImageView image = (ImageView)convertView.findViewById(mMapper.imageViewId);
		
		//Loading image with Picaso library
		PicasoHalper.loadImage(mContext, image, holder.getImageUrl());
		
		return convertView;
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
