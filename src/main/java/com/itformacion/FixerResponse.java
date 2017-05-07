package com.itformacion;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FixerResponse {
	private String base;
	private String date;
	private Map<String, Double> rates;
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String s  = null;
		try {
			s = mapper.setSerializationInclusion(Include.NON_NULL)
			    .writerWithDefaultPrettyPrinter()
			    .writeValueAsString(this);
		} catch (JsonProcessingException e) {
		}
		return s;
	}
}
