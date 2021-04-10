package com.alpsakaci.applemusic2spotify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class MusicLibraryController {

	@Autowired
	private LibraryParseService libraryParseService;


	@PostMapping
	@RequestMapping("/uploadLibrary")
	void handleLibraryFileUpload(@RequestParam("libraryFile") MultipartFile file) {
		List<Track> tracks = libraryParseService.getTrackList(file);
		System.out.println(tracks.size());
		
	}

}
