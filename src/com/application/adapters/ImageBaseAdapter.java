package com.application.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.application.imageholders.ImageHolder;
import com.application.imageholders.ImageLoader;
import com.application.imageholders.ImageLoader.APImageListener;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;

public class ImageBaseAdapter extends BaseAdapter {

	public static final String TAG = "ImageBaseAdapter";

	private static final String CONVERTED = "convertedImage";
	
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
		
		if (convertView == null) {
			convertView = inflater.inflate(OSUtil.getLayoutResourceIdentifier(mMapper.itemLayoutName), parent, false);
			
		}
		else {
			((ImageView) convertView.findViewById(mMapper.imageViewId)).setImageDrawable(null);
			ImageLoader convertedLoader = (ImageLoader)convertView.getTag(OSUtil.getStringResourceIdentifier("image_loader_custom_tag"));
			if(convertedLoader!= null){
				convertedLoader.cancel();
			}
			convertView.setTag(OSUtil.getStringResourceIdentifier("converted_view_custom_tag"), CONVERTED); 
		}
		
		convertView.setTag(holder);
		String imageId = holder.getID();
		
		ImageView imageView = (ImageView) convertView.findViewById(mMapper.imageViewId);
		imageView.setTag(imageId);
		
		
		ImageLoader imageLoader = new ImageLoader(holder, new ImageLoaderListener(imageView, imageId, position));
		if (mImageWidth > 0 && mImageHeight > 0) {
			imageLoader.loadScaledImages(mImageWidth, mImageHeight);
		} else {
			imageLoader.loadImages();
		}
		
		convertView.setTag(OSUtil.getStringResourceIdentifier("image_loader_custom_tag"), imageLoader); // Set the image loader as a custom tag on the item row
		
		 
		return convertView;
		
//		ImageHolder holder = getItem(position);
//		
//		if(convertView == null){
//			convertView = inflater.inflate(OSUtil.getLayoutResourceIdentifier(mMapper.itemLayoutName), parent, false);
//		}
//		
//		ImageView image = (ImageView)convertView.findViewById(mMapper.imageViewId);
//		
//		//Loading image with Picaso library
//		PicasoHalper.loadImage(mContext, image, holder.getImageUrl());
//		
//		return convertView;
	}
	
private class ImageLoaderListener implements  APImageListener {
		
		private ImageView mImageView;
		private String mImageId;
		private int mPosition;

		public ImageLoaderListener(ImageView imageView, String imageId, int position) {
			mImageView = imageView;
			mImageId = imageId;
			mPosition = position;
		}
		
		@Override
		public void handleException(Exception e) {
			
			//if the image not loaded then set the place holder in the image view.
			String imageId = (String) mImageView.getTag();
			if (!StringUtil.isEmpty(mPlaceHolder) && imageId.equals(mImageId)) { // Verify that we are setting the drawable on the correct ImageView, in case the user scrolled the grid/list
				mImageView.setImageResource(OSUtil.getDrawableResourceIdentifier(mPlaceHolder));
			}
			
		}



		@Override
		public void onTaskComplete(ImageHolder[] result) {
			String imageId = (String) mImageView.getTag();
			if (imageId.equals(mImageId)) { // Verify that we are setting the drawable on the correct ImageView, in case the user scrolled the grid/list
				mImageView.setImageDrawable(result[0].getDrawable());
				mData.get(mPosition).setDrawable(result[0].getDrawable());
			}
		}

		@Override
		public void onTaskStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRequestSent(ImageHolder imageHolder) {
			// TODO Auto-generated method stub
			
		}
		
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
