package com.alpsakaci.applemusic2spotify.spotify;

import com.alpsakaci.applemusic2spotify.spotify.model.CreatePlaylistDto;
import com.alpsakaci.applemusic2spotify.spotify.model.Playlist;
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

	public Playlist createPlaylist(String name) {
		CreatePlaylistDto playlist = new CreatePlaylistDto();
		playlist.setName(name);

		Playlist response = this.restTemplate.postForObject(spotifyApiConstants.getUserPlaylistsUrl(), playlist,
				Playlist.class);

		return response;
	}

}
