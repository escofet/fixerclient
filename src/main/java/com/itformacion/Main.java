package com.itformacion;

import java.io.IOException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
	public static void main(String[] args) throws IOException, UnirestException {
		/*
		System.setProperty("http.proxyHost", "proxy.itformacion.com");
        System.setProperty("http.proxyPort", "8080");
        
        or
        
		SocketAddress addr = new InetSocketAddress("proxy.itformacion.com", 8080);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
		*/
		
		// JSOUP
		/*
		String json = Jsoup.connect("http://api.fixer.io/latest?base=EUR")
			.ignoreContentType(true)
			.execute()
			.body();
		FixerResponse fix = new Gson().fromJson(json, FixerResponse.class);
		Gson prettyFixer = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(prettyFixer.toJson(fix));
        */
		
        // UNIREST
        HttpResponse<FixerResponse> fixResponse = UnirestGsonClient.get("http://api.fixer.io/latest")
			.header("accept", "application/json")
			.queryString("base", "EUR")
			.asObject(FixerResponse.class);
        FixerResponse myFixer = fixResponse.getBody();
        System.out.println(myFixer);
        
        // OKHTTP
        /*
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
		    .scheme("http")
		    .host("api.fixer.io")
		    .addPathSegment("latest")
		    .addQueryParameter("base", "EUR")
		    .build();
		Request request = new Request.Builder()
			.url(url)
		    .build();
		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		}
		*/
        
		// RESTTEMPLATE
        /*
		RestTemplate restTemplate = new RestTemplate();
		FixerResponse fixSpring = restTemplate.getForObject(
			"http://api.fixer.io/latest?base={currency}", FixerResponse.class, "EUR"
		);
		System.out.println(prettyFixer.toJson(fixSpring));
		*/
	}
}
