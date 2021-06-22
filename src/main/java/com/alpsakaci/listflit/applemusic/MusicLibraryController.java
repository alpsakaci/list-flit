package com.alpsakaci.listflit.applemusic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpsakaci.listflit.applemusic.model.AppleMusicLibrary;
import com.alpsakaci.listflit.applemusic.model.PlaylistItemsDto;
import com.alpsakaci.listflit.migrator.SpotifyMigrator;

@RestController
@RequestMapping("/library")
public class MusicLibraryController {

	@Autowired
	private LibraryParseService libraryParseService;
	
	@Autowired
	private SpotifyMigrator spotifyMigrator;

	@PostMapping
	AppleMusicLibrary handleLibraryFileUpload(@RequestParam("libraryFile") MultipartFile file) {
		return libraryParseService.parseLibrary(file);
	}

	@PostMapping("importPlaylist")
	PlaylistItemsDto importPlaylist(@RequestBody PlaylistItemsDto applePlaylist) {
		spotifyMigrator.migratePlaylist(applePlaylist.getPlaylist(), applePlaylist.getTracks());

		return applePlaylist;
	}

}
