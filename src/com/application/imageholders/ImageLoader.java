package com.application.imageholders;

import java.net.URL;
import java.util.ArrayList;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.application.app.CustomApplication;
import com.application.listener.AsyncTaskListener;


public class ImageLoader {

	private com.android.volley.toolbox.ImageLoader _volleyImageLoader;
	private static BitmapLruCache mInAppCache;
	private static RequestQueue mRequestQueue;
	private ArrayList<ImageContainer> _imageContainers;
	private APImageListener _imageListener;
	private ImageHolder[] imageHolders;
	/**
	 * used to hold how many of the imageHolders were loaded so far. (used only on old implementation)
	 */
	private int _loadCount = 0;
	
	
	/**
	 * Flag if the loading should be of a scaled image.
	 */
	private boolean _shouldLoadScaled = false;
	private int imgWidth = 0;
	private int imgHeight = 0;
	
	public ImageLoader(ImageHolder imageHolder, APImageListener imageListener){
		this(imageListener,new ImageHolder[]{imageHolder});
	}
	
	public ImageLoader(APImageListener imageListener,ImageHolder... imageHolders){
	//	checkMainThread();	
		this.imageHolders = imageHolders;
		this._imageListener = imageListener;
		this._imageContainers = new ArrayList<ImageContainer>();		
		initVolleyIfNeeded();		
		this._volleyImageLoader = new com.android.volley.toolbox.ImageLoader(mRequestQueue, mInAppCache);		
	}
	

	
	public void loadImages(){
		loadImagesWithVolley();
	}
	
	public void loadScaledImages(int imageWidth, int imageHeight){
		_shouldLoadScaled = true;

		imgWidth = imageWidth;
		imgHeight = imageHeight;

		loadImagesWithVolley();
	}
	
	public void cancel(){
		for(ImageContainer container : this._imageContainers){
			container.cancelRequest();
		}
	}

	private void loadImagesWithVolley(){
//		checkMainThread();
		
		
		if(this._imageListener == null){
			throw new IllegalStateException("Your APImageListener can't be null.");
		}
		
		for(int i = 0; i < imageHolders.length; i++){
			final ImageHolder holder = imageHolders[i];
			String imageURL = holder.getImageUrl();
			final int imageIndex = i;

			ImageListener volleyImageListener = new ImageListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					increaseLoadCount(error);
				}

				@Override
				public void onResponse(ImageContainer response, boolean isImmediate) {
					if(response.getBitmap() == null){
						_imageListener.onRequestSent(holder);
					}
					else{
						imageHolders[imageIndex].setDrawable(new BitmapDrawable(response.getBitmap()));
						increaseLoadCount(null);
					}					
				}
			};
			
			ImageContainer imageContainer = null;
			
			try{
				// Instead of waiting for volley to crash an show an ugly log we validate the url before
				new URL(imageURL);
				
				if (_shouldLoadScaled){
					boolean shouldStartWithQmark = !imageURL.contains("?");
					StringBuilder sb = new StringBuilder(imageURL);
					sb.append(shouldStartWithQmark ? "?" : "&").append("height=").append(imgHeight).append("&width=").append(imgWidth);
					imageURL = sb.toString();
					imageContainer = _volleyImageLoader.get(imageURL, volleyImageListener, imgWidth, imgHeight);
				}
				else{
					imageContainer = _volleyImageLoader.get(imageURL, volleyImageListener);
				}
				Log.d(ImageLoader.class.getSimpleName(), imageURL);
				this._imageContainers.add(imageContainer);
			}
			catch (Exception e){
				increaseLoadCount(new Exception(e));			
				
			}
			
			
		}
	}
	
	private void initVolleyIfNeeded() {
		// This code will run on UI thread so there is no need in synchronization.
		if(mRequestQueue == null){
			mRequestQueue = Volley.newRequestQueue(CustomApplication.getAppContext());				
		}
		if(mInAppCache == null){
			Context context = CustomApplication.getAppContext();
			ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			int memoryClass = am.getMemoryClass() * 1024 * 1024;
			
			// we set the in-App cache to third of the available memory.
			mInAppCache = new BitmapLruCache(memoryClass / 6);
		}
	}
	
	private void checkMainThread() {
		// This constructor must be called from the main thread. 
		if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked on the main thread.");
        }
	}
	

	
	public static interface APImageListener extends AsyncTaskListener<ImageHolder[]>{
		
		public void onRequestSent(ImageHolder imageHolder);		

	}

	private void increaseLoadCount(Exception exceptoin){
		_loadCount++;
		if(_loadCount  == imageHolders.length){		
			if(exceptoin == null  || _loadCount > 1)
			{
				_imageListener.onTaskComplete(imageHolders);
			}
			else
			{
				_imageListener.handleException(exceptoin);
			}
		
		}
	}
	
	public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {
	    public BitmapLruCache(int maxSize) {
	        super(maxSize);
	    }


	    @Override
	    protected int sizeOf(String key, Bitmap value) {
	        return value.getRowBytes() * value.getHeight();
	    }


	    //@Override
	    public Bitmap getBitmap(String url) {
	        Bitmap temp = get(url);
	        if(temp != null && !temp.isRecycled()){
	        	return temp;
	        }
	        else{
	        	return null;
	        }
	    }


	    //@Override
	    public void putBitmap(String url, Bitmap bitmap) {
	        put(url, bitmap);
	    }
	}

}
