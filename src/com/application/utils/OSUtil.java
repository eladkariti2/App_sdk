package com.application.utils;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.application.app.CustomApplication;
import com.application.text.APConstant;

public class OSUtil {



	private static final String TAG = "OSUtil";

	public static String getDeviceIdentifier(Context context) {
		String udid = Settings.Secure.getString(context.getContentResolver(),
				Settings.Secure.ANDROID_ID);
		if (StringUtil.isEmpty(udid) || "9774d56d682e549c".equals(udid)) {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			udid = tm.getDeviceId();
			if (StringUtil.isEmpty(udid)) {
				udid = tm.getSimSerialNumber();
			}
			udid = StringUtil.isEmpty(udid) ? null : udid;
		}
		return udid;
	}


	public static int convertDPToPixels(float dp) {
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				CustomApplication.getAppContext().getResources()
						.getDisplayMetrics());
		return (int) px;
	}

	public static int convertDPToPixels(int dp) {
		return convertDPToPixels((float) dp);
	}

	public static void setLocale(Context context,String localString){
		Locale locale = new Locale(localString);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		context.getResources().updateConfiguration(config,
				context.getResources().getDisplayMetrics());

	}

	public static void launchMail(Context context,String[] addresses, String subject, String body, boolean isHtml) {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("text/html");
		if(addresses != null){
			emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
		}
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, isHtml ? Html.fromHtml(body) : body);

		startEmailActivity(context, emailIntent);
	}

	private static void startEmailActivity(Context context, Intent emailIntent) {
		try{
			if(!(context instanceof Activity)){
				emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
			context.startActivity(emailIntent);
		}
		catch (Exception e){
			Log.e(TAG, "Error when trying to send mail - startEmailActivity");
		}
	}

	public static void launchCamera(Context context,Uri imageUri){
	
         Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
       
		//Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (intent.resolveActivity(context.getPackageManager()) != null) {
			((Activity) context).startActivityForResult(intent, APConstant.REQUEST_IMAGE_CAPTURE_CAMERA);
		}
	}

	public static void launchCamera(Fragment f,Context context,Uri imageUri){
		
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
      
		//Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (intent.resolveActivity(context.getPackageManager()) != null) {
			f.startActivityForResult(intent, APConstant.REQUEST_IMAGE_CAPTURE_CAMERA);
		}
	}
	
	public static void launchGallery(Context context){
		// Filesystem.
		final Intent galleryIntent = new Intent();
		galleryIntent.setType("image/*");
		galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

		// Chooser of filesystem options.
		final Intent chooserIntent = Intent.createChooser(galleryIntent, "BOlayam");
		if (chooserIntent.resolveActivity(context.getPackageManager()) != null) {
			((Activity) context).startActivityForResult(chooserIntent, APConstant.REQUEST_IMAGE_CAPTURE_GALLERY);
		}
	}

	public static String getAppVersion(Context context) {
		String result = "";
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			result = info.versionName;
		} catch (Exception e) {

		}
		return result;
	}

	public static String getDeviceModel() {
		return android.os.Build.MODEL;
	}

	public static int getAppVersionCode(Context context) {
		int result = 0;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			result = info.versionCode;
		} catch (Exception e) {

		}
		return result;
	}

	public static int getAPIVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	public static int getScreenHeight(Context context) {
		return ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
				.getHeight();
	}

	public static int getScreenWidth(Context context) {
		return ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
				.getWidth();
	}

	public static String getDeviceData(Context context) {
		StringBuffer deviceData = new StringBuffer();

		deviceData.append("Package: ").append(OSUtil.getPackageName(context))
		.append("\n");
		deviceData.append("Version name: ")
		.append(OSUtil.getAppVersion(context)).append("\n");
		deviceData.append("Version code: ")
		.append(OSUtil.getAppVersionCode(context)).append("\n");
		deviceData.append("Device model: ").append(OSUtil.getDeviceModel())
		.append("\n");
		deviceData.append("OS API Version: ").append(OSUtil.getAPIVersion())
		.append("\n");
		deviceData.append("Screen size: ")
		.append(OSUtil.getScreenWidth(context)).append("x")
		.append(OSUtil.getScreenHeight(context)).append("\n");
		deviceData.append("Screen density: ")
		.append(OSUtil.getScreenDensity(context)).append("\n");
		return deviceData.toString();
	}

	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}



	public static String getPackageName() {
		return getPackageName(CustomApplication.getAppContext());
	}

	public static String getPackageName(Context context) {
		return context.getPackageName();
	}


	public static String getBundleId() {
		return getPackageName();
	}


	public static int getAttributeResourceIdentifier(String name) {
		return getResourceIdentifier(name, "attr");
	}

	// Find resource id at runtime
	public static int getResourceId(String name) {
		return getResourceIdentifier(name, "id");
	}

	public static int getAnimationResourceId(String name) {
		return getResourceIdentifier(name, "anim");
	}

	public static int getLayoutResourceIdentifier(String name) {
		return getResourceIdentifier(name, "layout");
	}

	public static int getDrawableResourceIdentifier(String name) {
		return getResourceIdentifier(name, "drawable");
	}

	public static int getStringResourceIdentifier(String name) {
		return getResourceIdentifier(name, "string");
	}

	// Find resource identifier 
	public static int getResourceIdentifier(String name,String defType){
		return CustomApplication.getAppContext().getResources().getIdentifier(name, defType, OSUtil.getPackageName());
	}


	public static void OpenFacebookIntent(Context context,String fbProfileId) {
		Intent i ;
		Uri uri ;

		String facebookUrl = "https://www.facebook.com/" + fbProfileId;
		try {
			int versionCode = context.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
			uri = Uri.parse(versionCode >= 3002850 ? "fb://facewebmodal/f?href=" + facebookUrl : "fb://profile/" + fbProfileId );
		} catch (PackageManager.NameNotFoundException e) {
			// Facebook is not installed. Open the browser
			uri = Uri.parse(facebookUrl);
		}
		i = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(i);
	}


	public static boolean isApplicationInstalled(String packageName) {
		try{
			ApplicationInfo info = CustomApplication.getAppContext().getPackageManager().
					getApplicationInfo(packageName, 0 );
			return true;
		} catch( PackageManager.NameNotFoundException e ){
			return false;
		}
	}

	public static void launchBrowswer(Activity context, String url) {
		try {
			if (!StringUtil.isEmpty(url)) {
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		} catch (Throwable t) {
			Log.e("LaunchBrowser", "Failed to open "+ url);
		}
	}

	public static boolean hasNetworkConnection(Context context) {
		ConnectivityManager conectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conectivityManager == null ? null
				: conectivityManager.getActiveNetworkInfo();
		boolean connected = networkInfo != null
				&& NetworkInfo.State.CONNECTED.equals(networkInfo.getState());
		return connected;
	}

}
