package com.application.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.application.imageholders.ImageHolder;
import com.application.imageholders.ImageLoader;
import com.application.interfaces.OnCustomImageLoaderListener;

import java.net.UnknownHostException;

/**
 * Created by elad on 9/19/2015.
 */

public class CustomImageLoader {
    private ImageView imageView;
    private ImageHolder imageHolder;
    private OnCustomImageLoaderListener listener;
    private int imagePlaceholder = 0;
    private static final int IMAGE_LOADER_TAG = 82826666;

    public CustomImageLoader(ImageView imageView, ImageHolder imageHolder) {
        this.imageView = imageView;
        this.imageHolder = imageHolder;
    }

    public CustomImageLoader(ImageView imageView, ImageHolder imageHolder, OnCustomImageLoaderListener listener) {
        this.imageView = imageView;
        this.imageHolder = imageHolder;
        this.listener = listener;
    }

    public CustomImageLoader(ImageView imageView, ImageHolder imageHolder, OnCustomImageLoaderListener listener, int imagePlaceholder) {
        this.imageView = imageView;
        this.imageHolder = imageHolder;
        this.listener = listener;
        this.imagePlaceholder = imagePlaceholder;
    }

    public void loadImage() {
        ImageLoader prevLoader = (ImageLoader) imageView.getTag(IMAGE_LOADER_TAG);
        if (prevLoader != null){
            prevLoader.cancel();
        }
        ImageLoader imageLoader = new ImageLoader(new ImageLoader.APImageListener() {

            @Override
            public void onTaskStart() {
            }

            @Override
            public void onTaskComplete(ImageHolder[] holder) {
                if (holder != null && holder.length > 0) {
                    Drawable drawable = holder[0].getDrawable();
                    if (drawable != null) {
                        imageView.setImageDrawable(drawable);
                        if (listener != null) {
                            listener.ImageLoaded();
                        }
                    }
                }
            }

            @Override
            public void handleException(Exception arg0) {
                if (imagePlaceholder != 0) {
                    imageView.setImageResource(imagePlaceholder);
                }
                else{
                    //create place holder
                   // imageView.setImageDrawable(CustomApplication.getAppContext().getResources().getDrawable(R.drawable.wrapper_cell_placeholder));
                }
            }

            @Override
            public void onRequestSent(ImageHolder arg0) {
            }
        }, imageHolder);

        imageView.setTag(IMAGE_LOADER_TAG, imageLoader);
        imageLoader.loadImages();
    }

    public void loadImage(int width, int height) {

        ImageLoader imageLoader = new ImageLoader(new ImageLoader.APImageListener() {

            @Override
            public void onTaskStart() {
            }

            @Override
            public void onTaskComplete(ImageHolder[] holder) {
                if (holder != null && holder.length > 0) {
                    Drawable drawable = holder[0].getDrawable();
                    if (drawable != null) {
                        imageView.setImageDrawable(drawable);
                        if (listener != null) {
                            listener.ImageLoaded();
                        }
                    }
                }
            }

            @Override
            public void handleException(Exception arg0) {
                if (arg0 instanceof UnknownHostException) {
                }
            }

            @Override
            public void onRequestSent(ImageHolder arg0) {
            }
        }, imageHolder);
        imageLoader.loadScaledImages(width, height);
    }
}
