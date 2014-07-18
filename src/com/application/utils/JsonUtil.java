package com.application.utils;

import com.google.gson.Gson;


public class JsonUtil {

	public static String deserialize(Object models,Class type) {
		String json = null;
		Gson gson = new Gson();
		json = gson.toJson(models, type);	
		
		return json;
	}

	public static Object serialize(String account,Class type) {
		Object obj  = null;
		Gson gson = new Gson();
		obj = gson.fromJson(account, type);	
		
		return obj;
	}

}
