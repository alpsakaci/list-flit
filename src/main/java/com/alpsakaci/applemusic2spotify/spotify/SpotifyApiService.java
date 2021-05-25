package com.alpsakaci.applemusic2spotify.spotify;

import java.util.List;

import com.alpsakaci.applemusic2spotify.spotify.model.CreatePlaylistDto;
import com.alpsakaci.applemusic2spotify.spotify.model.SpotifyPlaylist;
import com.alpsakaci.applemusic2spotify.spotify.model.SearchTrackResponse;
import com.alpsakaci.applemusic2spotify.spotify.model.SpotifyTrack;
import com.alpsakaci.applemusic2spotify.spotify.model.TracksResponse;
import com.alpsakaci.applemusic2spotify.spotify.model.URIsDto;
import com.alpsakaci.applemusic2spotify.spotify.model.SpotifyUser;

public class SpotifyApiService extends ApiBinding {

	private SpotifyApiConstants spotifyApiConstants;

	public SpotifyApiService(String accessToken, SpotifyApiConstants spotifyApiConstants) {
		super(accessToken);
		this.spotifyApiConstants = spotifyApiConstants;
	}

	public SpotifyUser me() {
		SpotifyUser user = this.restTemplate.getForObject(spotifyApiConstants.getUserProfileUrl(), SpotifyUser.class);

		return user;
	}

	public SpotifyTrack searchTrack(String trackName) {
		SearchTrackResponse response = this.restTemplate
				.getForObject(spotifyApiConstants.getSearchTrackUrl() + trackName, SearchTrackResponse.class);

		TracksResponse tracksResponse = response.getTracks();

		if (tracksResponse.getItems().length != 0) {
			return tracksResponse.getItems()[0];
		}

		return null;
	}

	public SpotifyPlaylist createPlaylist(String name) {
		CreatePlaylistDto playlist = new CreatePlaylistDto();
		playlist.setName(name);

		SpotifyPlaylist response = this.restTemplate.postForObject(spotifyApiConstants.getUserPlaylistsUrl(), playlist,
				SpotifyPlaylist.class);

		return response;
	}

	private URIsDto createURIs(List<SpotifyTrack> tracks) {
		String[] uris = new String[tracks.size()];

		for (int i = 0; i < tracks.size(); i++) {
			uris[i] = tracks.get(i).getUri();
		}

		return new URIsDto(uris);
	}

	public Object addItemsToPlaylist(SpotifyPlaylist playlist, List<SpotifyTrack> tracks) {
		URIsDto uris = createURIs(tracks);

		return this.restTemplate.postForObject("https://api.spotify.com/v1/playlists/" + playlist.getId() + "/tracks",
				uris, Object.class);
	}

}
