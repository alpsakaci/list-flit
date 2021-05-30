package com.alpsakaci.listflit.spotify;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alpsakaci.listflit.applemusic.LibraryParseService;
import com.alpsakaci.listflit.applemusic.model.AppleMusicTrack;
import com.alpsakaci.listflit.applemusic.model.PlaylistItemsDto;
import com.alpsakaci.listflit.spotify.model.CreatePlaylistDto;
import com.alpsakaci.listflit.spotify.model.SearchTrackResponse;
import com.alpsakaci.listflit.spotify.model.SpotifyPlaylist;
import com.alpsakaci.listflit.spotify.model.SpotifyTrack;
import com.alpsakaci.listflit.spotify.model.SpotifyUser;
import com.alpsakaci.listflit.spotify.model.TracksResponse;
import com.alpsakaci.listflit.spotify.model.URIsDto;

public class SpotifyApiService extends ApiBinding {
	
	@Autowired
	private LibraryParseService libraryParseService;

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
	
	public void importPlaylist(PlaylistItemsDto applePlaylist) {
		Map<Integer, AppleMusicTrack> appleTracksMap = libraryParseService.convertListToMap(applePlaylist.getTracks());
		SpotifyPlaylist spotifyPlaylist = this.createPlaylist(applePlaylist.getPlaylist().getName());
		List<SpotifyTrack> spotifyTracks = new LinkedList<SpotifyTrack>();

		for (Integer trackId : applePlaylist.getPlaylist().getPlaylistItems()) {
			AppleMusicTrack appleTrack = appleTracksMap.get(trackId);
			SpotifyTrack spotifyTrack = this.searchTrack(appleTrack.buildQueryString());
			if (spotifyTrack != null) {
				spotifyTracks.add(spotifyTrack);
			}
		}

		this.addItemsToPlaylist(spotifyPlaylist, spotifyTracks);
	}

}
