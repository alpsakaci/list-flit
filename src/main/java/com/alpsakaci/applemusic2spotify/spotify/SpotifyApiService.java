package com.alpsakaci.applemusic2spotify.spotify;

import com.alpsakaci.applemusic2spotify.spotify.model.User;

public class SpotifyApiService extends ApiBinding {

	private SpotifyApiConstants spotifyApiConstants;

	public SpotifyApiService(String accessToken, SpotifyApiConstants spotifyApiConstants) {
		super(accessToken);
		this.spotifyApiConstants = spotifyApiConstants;
	}

	public User me() {
		User user = this.restTemplate.getForObject(spotifyApiConstants.getUserProfileUrl(), User.class);

		return user;
	}

}
