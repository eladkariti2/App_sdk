package com.application.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.application.utils.OSUtil;

public class APWebView extends WebView{

	public static final String TAG = APWebView.class.getSimpleName();
	//private JSInterface jsInterface;
	private Handler JSHandler;

	public APWebView(Context context) {
		super(context);
	}

	public APWebView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	private void initWebView(Context context, String urlToLoad, boolean enableZoom,final int exposureTime) {
		
		this.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		this.getSettings().setJavaScriptEnabled(true);
		this.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		this.getSettings().setPluginState(PluginState.ON);
		this.getSettings().setUseWideViewPort(true);
		this.setBackgroundColor(0x00000000);
	
		this.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				boolean result = true;
//				if(isKnownScheme(url)){
//					handleSpecialUrl(view.getContext(),url);
//					result = true;
//				}
//				else{
//					APWebView.this.loadUrl(url);
//					result =  true;
//				}
				APWebView.this.loadUrl(url);
				return result;
			} 
			public void onReceivedError (WebView view, int errorCode, String description, String failingUrl){
				Log.e(TAG, failingUrl +"   "+errorCode);
			}
			public void onPageFinished(WebView view, String url) {
			
				Log.d(TAG,"onPageFinished   "+url);
			
				if( getContext() != null ){
					View progBar = ((Activity) getContext()).findViewById(OSUtil.getResourceId("progressbar") );
					//When you go back from the webView quick before the callback invoke the view is null.
					if(progBar != null){
						progBar.setVisibility(View.GONE);
					}
					setTimerIfNeeded(exposureTime);
				}
			}

		});

		//JSHandler = initJSHandler((Activity) context);
	//	jsInterface = JSInterface.getInstance(context, this,JSHandler,AppData.getProperty(PropertiesI.FACEBOOK_ID_KEY));
		
		this.loadUrl(urlToLoad);
	}


	protected void setTimerIfNeeded(int exposureTime) {
		// TODO Auto-generated method stub
		if(exposureTime >0 ){
			Timer viewTimer = new Timer();
			viewTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					closeActivity();
				}
			}, exposureTime * 1000);
		}
	}

	protected void closeActivity() {
		// TODO Auto-generated method stub
		if(getContext() != null && getContext() instanceof Activity){
			((Activity)getContext()).finish();
		}
	}

	public void loadUrl(Context context, String url, boolean enableZoom){
		loadUrl(context,url,enableZoom,0);
	}
	
	public void loadUrl(Context context, String url, boolean enableZoom,int exposureTime){
		initWebView(context, url, enableZoom,exposureTime);
	}


	


	

}
