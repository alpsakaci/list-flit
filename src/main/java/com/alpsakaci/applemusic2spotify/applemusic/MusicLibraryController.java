package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpsakaci.applemusic2spotify.spotify.SpotifyApiService;
import com.alpsakaci.applemusic2spotify.spotify.model.SpotifyPlaylist;
import com.alpsakaci.applemusic2spotify.spotify.model.SpotifyTrack;

@RestController
@RequestMapping("/library")
public class MusicLibraryController {

	@Autowired
	private LibraryParseService libraryParseService;

	@Autowired
	private SpotifyApiService apiService;

	@PostMapping
	LibraryDto handleLibraryFileUpload(@RequestParam("libraryFile") MultipartFile file) {
		List<AppleMusicTrack> tracks = libraryParseService.getTracks(file);
		List<AppleMusicPlaylist> playlists = libraryParseService.getPlaylists(file);

		return new LibraryDto(tracks, playlists);
	}

	@PostMapping("/import")
	LibraryDto importToSpotify(@RequestBody LibraryDto library) {
		Map<Integer, AppleMusicTrack> appleTracksMap = libraryParseService.convertListToMap(library.getTracks());

		for (AppleMusicPlaylist applePlaylist : library.getPlaylists()) {
			SpotifyPlaylist spotifyPlaylist = apiService.createPlaylist(applePlaylist.getName());
			List<SpotifyTrack> spotifyTracks = new LinkedList<SpotifyTrack>();

			for (Integer trackId : applePlaylist.getPlaylistItems()) {
				AppleMusicTrack appleTrack = appleTracksMap.get(trackId);
				SpotifyTrack spotifyTrack = apiService.searchTrack(appleTrack.buildQueryString());
				if (spotifyTrack != null) {
					spotifyTracks.add(spotifyTrack);
				}
			}

			apiService.addItemsToPlaylist(spotifyPlaylist, spotifyTracks);
		}

		return library;
	}

	@PostMapping("importPlaylist")
	PlaylistItemsDto importPlaylistToSpotify(@RequestBody PlaylistItemsDto applePlaylist) {
		Map<Integer, AppleMusicTrack> appleTracksMap = libraryParseService.convertListToMap(applePlaylist.getTracks());
		SpotifyPlaylist spotifyPlaylist = apiService.createPlaylist(applePlaylist.getPlaylist().getName());
		List<SpotifyTrack> spotifyTracks = new LinkedList<SpotifyTrack>();

		for (Integer trackId : applePlaylist.getPlaylist().getPlaylistItems()) {
			AppleMusicTrack appleTrack = appleTracksMap.get(trackId);
			SpotifyTrack spotifyTrack = apiService.searchTrack(appleTrack.buildQueryString());
			if (spotifyTrack != null) {
				spotifyTracks.add(spotifyTrack);
			}
		}

		apiService.addItemsToPlaylist(spotifyPlaylist, spotifyTracks);

		return applePlaylist;
	}

}
