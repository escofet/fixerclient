package com.itformacion;
/*
Unirest HTTP Client - Using Jackson Mapper (Thread-safe Singleton) 
*/
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

public final class UnirestClient {
	static {
		Unirest.setObjectMapper(new ObjectMapper() {
		    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = 
	    		new com.fasterxml.jackson.databind.ObjectMapper();
		
		    public <T> T readValue(String value, Class<T> valueType) {
		        try {
		            return jacksonObjectMapper.readValue(value, valueType);
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
		    }
		
		    public String writeValue(Object value) {
		        try {
		            return jacksonObjectMapper.writeValueAsString(value);
		        } catch (JsonProcessingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		});
	}
	private static Unirest instance = null;
	
	private UnirestClient() {}
	
    public static Unirest getInstance() {
        Unirest localInstance = instance;
        if (localInstance == null) {
            synchronized (Unirest.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Unirest();
                }
            }
        }
        return localInstance;
    }
}
