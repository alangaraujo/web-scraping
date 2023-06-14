package br.com.alangaraujo.infrastructure.resource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProviderVerifier {

	public boolean isPublicRepository(String fullUrl) throws IOException {
		URL url = new URL(fullUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		return (connection.getResponseCode() == HttpStatus.OK.value());
	}
	
}
