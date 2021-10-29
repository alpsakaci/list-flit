package com.alpsakaci.listflit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConfig {

	@Value("${spotify.client.id}")
	private String spotifyClientId;

	@Value("${spotify.client.secret}")
	private String spotifyClientSecret;

	public String getClientId() {
		return this.spotifyClientId;
	}

	public String getClientSecret() {
		return this.spotifyClientSecret;
	}
	
}
