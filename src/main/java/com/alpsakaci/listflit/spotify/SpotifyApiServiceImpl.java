package com.alpsakaci.listflit.spotify;

import java.util.List;

import com.alpsakaci.listflit.spotify.model.CreatePlaylistDto;
import com.alpsakaci.listflit.spotify.model.SearchTrackDto;
import com.alpsakaci.listflit.spotify.model.SpotifyPlaylist;
import com.alpsakaci.listflit.spotify.model.SpotifyTrack;
import com.alpsakaci.listflit.spotify.model.SpotifyTracksDto;
import com.alpsakaci.listflit.spotify.model.SpotifyUser;
import com.alpsakaci.listflit.spotify.model.URIsDto;

public class SpotifyApiServiceImpl extends ApiBinding implements SpotifyApiService {

	private SpotifyApiConstants spotifyApiConstants;

	public SpotifyApiServiceImpl(String accessToken, SpotifyApiConstants spotifyApiConstants) {
		super(accessToken);
		this.spotifyApiConstants = spotifyApiConstants;
	}

	public SpotifyUser me() {
		SpotifyUser user = this.restTemplate.getForObject(spotifyApiConstants.getUserProfileUrl(), SpotifyUser.class);

		return user;
	}

	public SpotifyTrack searchTrack(String query) {
		SearchTrackDto response = this.restTemplate.getForObject(spotifyApiConstants.getSearchTrackUrl() + query,
				SearchTrackDto.class);

		SpotifyTracksDto tracksResponse = response.getTracks();

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

	public void addItemsToPlaylist(SpotifyPlaylist playlist, List<SpotifyTrack> tracks) {

		if (tracks.size() > 100) {
			List<List<SpotifyTrack>> partitions = Partition.ofSize(tracks, 100);

			for (List<SpotifyTrack> subList : partitions) {
				URIsDto uris = createURIs(subList);
				this.restTemplate.postForObject("https://api.spotify.com/v1/playlists/" + playlist.getId() + "/tracks",
						uris, Object.class);
			}

		} else {
			URIsDto uris = createURIs(tracks);
			this.restTemplate.postForObject("https://api.spotify.com/v1/playlists/" + playlist.getId() + "/tracks",
					uris, Object.class);
		}

	}

}
