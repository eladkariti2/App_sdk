package com.application.facebook.opengraph;

import android.content.Context;

import com.application.facebook.util.FacebookUtil;

public class FbOpenGraphUrlFactory  {
	//https://graph.facebook.com/me?fields=id,name,picture&access_token=CAAKkhYMCa7gBAGWaxjfxPb3RWUc1PZBrUy06tEkIOsBEN12OCHWZCM7oxUcb1WjhUqQm5LqGRHBnioznElnshEVAZCl7oAP1LStaKdtPpQ5ZAai8eJ8H67bPomkMmrO2VZB731l5oj7nszL9cSUGd8obh27AaYTMfbtqLoBkb9J6GdGVvSEmuZAJZBrhcrA15ERE9AeLkkzg7dVPJ2CeMn4a4jaeZAm0BSS6gqZAA63aWwgZDZD
	public static final String OPEN_GRAPH_BASE_URL = "https://graph.facebook.com/v2.1/";
	
	public static String getFacebokUserBasicURL(Context context){
		String ans = OPEN_GRAPH_BASE_URL  + "me?fields=id,name,picture&access_token=" + FacebookUtil.getFBAuthToken(context); ;
		
		
		return ans;
	}
	
	
	
	public enum re{
	
	}
	
	
}
