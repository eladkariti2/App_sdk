package com.application.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.application.interfaces.AccountLoadI;
import com.application.listener.AccountLoaderListener;
import com.application.loader.ModelLoader;
import com.application.models.AccountModel;
import com.application.ui.SplashLoader;
import com.application.utils.AppData;
import com.application.utils.OSUtil;

public abstract class BaseSplashActivity extends Activity implements AccountLoadI{

	private SplashLoader mSplashLoader; 
	protected AccountModel mAccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(OSUtil.getLayoutResourceIdentifier("splash_layout"));
		mSplashLoader = (SplashLoader) findViewById(OSUtil.getResourceId("splash_loader"));
		ImageView loaderAnimation = (ImageView) findViewById(OSUtil.getResourceId("progress_indicator"));
		AnimationDrawable progressAnimation = (AnimationDrawable) loaderAnimation.getDrawable();
		progressAnimation.start();
		ModelLoader.AccountModelLoader(new AccountLoaderListener(this));
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	Timer timer;

	@Override
	public void onAccountLoaded(AccountModel account) {
		mAccount = account;
		AppData.getInstace().setAccount(account);
		timer = new Timer();
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				onIntroLoaded();
			}
		}, 2000);
		
		
		
	}
	
	protected abstract void onIntroLoaded();
	 
	
	

}
