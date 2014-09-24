package com.application.facebook.opengraph;

import android.content.Context;

import com.application.facebook.util.FacebookUtil;

public class FbOpenGraphUrlFactory  {
	//https://graph.facebook.com/me?fields=id,name,picture&access_token=CAAKkhYMCa7gBAGWaxjfxPb3RWUc1PZBrUy06tEkIOsBEN12OCHWZCM7oxUcb1WjhUqQm5LqGRHBnioznElnshEVAZCl7oAP1LStaKdtPpQ5ZAai8eJ8H67bPomkMmrO2VZB731l5oj7nszL9cSUGd8obh27AaYTMfbtqLoBkb9J6GdGVvSEmuZAJZBrhcrA15ERE9AeLkkzg7dVPJ2CeMn4a4jaeZAm0BSS6gqZAA63aWwgZDZD
	public static final String OPEN_GRAPH_BASE_URL = "https://graph.facebook.com/v2.1/";
	public static final String OPEN_GRAPH_PIC_URL = "me?fields=id,name,picture.type(large)";
	public static final String ACCESS_TOKEN = "&access_token={{token}}";
	public static final String OPEN_GRAPH_FEED_URL = "{{ID}}?fields=id,name,feed.limit(25).since({{since}}){from{name,picture.type(large)},id,caption,message,picture,comments.summary(true){created_time,like_count,message,id,from{name,picture.type(large)}},likes.limit(1).summary(true)}";
	
	public static String getFacebokUserBasicURL(Context context){
		String ans = OPEN_GRAPH_BASE_URL  + OPEN_GRAPH_PIC_URL  + ACCESS_TOKEN;
		ans =  ans.replace("{{token}}", FacebookUtil.getFBAuthToken(context));
		return ans;
	}
	
	public static String getFacebokFeedBasicURL(Context context,String pageID,String date){
		String ans = OPEN_GRAPH_BASE_URL  + OPEN_GRAPH_FEED_URL + ACCESS_TOKEN;
		ans =  ans.replace("{{token}}", FacebookUtil.getFBAuthToken(context));
		ans = ans.replace("{{ID}}", pageID);
		ans = ans.replace("{{since}}", date);
		
		return ans;
	}
	
}
