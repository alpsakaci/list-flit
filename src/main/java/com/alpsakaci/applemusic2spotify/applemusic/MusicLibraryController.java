package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@EnableAutoConfiguration
@RequestMapping("/library")
public class MusicLibraryController {

	@Autowired
	private LibraryParseService libraryParseService;

	@GetMapping
	String library() {
		return "applemusic/uploadLibraryForm";
	}

	@PostMapping
	String handleLibraryFileUpload(@RequestParam("libraryFile") MultipartFile file, Model model) {
		List<Track> tracks = libraryParseService.getTrackList(file);

		for (Track track : tracks) {
			System.out.println(track);
		}

		System.out.println(tracks.size());

		model.addAttribute("tracks", tracks);

		return "applemusic/listTracks";
	}

}
