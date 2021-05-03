package com.alpsakaci.applemusic2spotify;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyApiService {

	private final String TOKEN_URL = "https://accounts.spotify.com/api/token";


	public void getAccessToken(String authCode) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("code", authCode);
		parameters.add("redirect_uri", SpotifyApiConfig.redirectUri);
		parameters.add("grant_type", "authorization_code");

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(SpotifyApiConfig.clientId, SpotifyApiConfig.clientSecret);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				parameters, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_URL, requestEntity, String.class);
		System.out.println(response.getBody());

	}

}
