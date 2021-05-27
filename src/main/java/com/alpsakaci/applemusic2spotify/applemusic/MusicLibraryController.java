package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		Map<Integer, AppleMusicTrack> tracks = libraryParseService.getTracks(file);
		List<AppleMusicPlaylist> playlists = libraryParseService.getPlaylists(file);

		LibraryDto dto = new LibraryDto();
		List<AppleMusicTrack> trackslist = new LinkedList<AppleMusicTrack>(tracks.values());
		dto.setPlaylists(playlists);
		dto.setTrakcs(trackslist);

		return dto;
	}

}
