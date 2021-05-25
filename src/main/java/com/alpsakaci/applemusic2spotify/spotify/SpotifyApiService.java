package com.alpsakaci.applemusic2spotify.spotify;

import com.alpsakaci.applemusic2spotify.spotify.model.CreatePlaylistDto;
import com.alpsakaci.applemusic2spotify.spotify.model.Playlist;
import com.alpsakaci.applemusic2spotify.spotify.model.SearchTrackResponse;
import com.alpsakaci.applemusic2spotify.spotify.model.Track;
import com.alpsakaci.applemusic2spotify.spotify.model.TracksResponse;
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

	public Track searchTrack(String trackName) {
		SearchTrackResponse response = this.restTemplate
				.getForObject(spotifyApiConstants.getSearchTrackUrl() + trackName, SearchTrackResponse.class);

		TracksResponse tracksResponse = response.getTracks();

		if (tracksResponse.getItems().length != 0) {
			return tracksResponse.getItems()[0];
		}

		return null;
	}

	public Playlist createPlaylist(String name) {
		CreatePlaylistDto playlist = new CreatePlaylistDto();
		playlist.setName(name);

		Playlist response = this.restTemplate.postForObject(spotifyApiConstants.getUserPlaylistsUrl(), playlist,
				Playlist.class);

		return response;
	}

}
