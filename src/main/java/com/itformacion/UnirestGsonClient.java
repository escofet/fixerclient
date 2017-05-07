package com.itformacion;
/*
Unirest HTTP Client - Using Gson Mapper (Delegation Pattern)
*/
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class UnirestGsonClient {
	static {
		Unirest.setObjectMapper(new ObjectMapper() {
	    	private Gson gson = new Gson();
	
	    	public <T> T readValue(String s, Class<T> aClass) {
	    		try {
	    			return gson.fromJson(s, aClass);
	    		} catch(Exception e) {
	    			throw new RuntimeException(e);
	    		}
	    	}
	    	public String writeValue(Object o) {
	    		try {
	    			return gson.toJson(o);
	    		} catch(Exception e) {
	    			throw new RuntimeException(e);
	    		}
	    	}
	    });
	}
	
	public static GetRequest get(String url) {
		return Unirest.get(url);
	}
	
	public static HttpRequestWithBody post(String url) {
		return new HttpRequestWithBody(HttpMethod.POST, url);
	}
	
	private UnirestGsonClient() {}
}
