package com.alpsakaci.listflit.spotify;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SpotifyApiConstants {

	private final String userProfileUrl = "https://api.spotify.com/v1/me";
	private final String userPlaylistsUrl = "https://api.spotify.com/v1/me/playlists";
	private final String searchTrackUrl = "https://api.spotify.com/v1/search?type=track&query=";

	public String getUserProfileUrl() {
		return userProfileUrl;
	}

	public String getUserPlaylistsUrl() {
		return userPlaylistsUrl;
	}

	public String getSearchTrackUrl() {
		return searchTrackUrl;
	}

}
