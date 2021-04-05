package com.alpsakaci.applemusic2spotify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Node;

import com.alpsakaci.applemusic2spotify.storage.StorageService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class MusicLibraryController {

	private final StorageService storageService;

	@Autowired
	private LibraryParseService libraryParseService;

	@Autowired
	public MusicLibraryController(StorageService storageService) {
		this.storageService = storageService;
	}

	@PostMapping
	@RequestMapping("/uploadLibrary")
	void handleLibraryFileUpload(@RequestParam("libraryFile") MultipartFile file) {
		List<Node> tracks = libraryParseService.getTracks(file);
		System.out.println(tracks.size());
	}

	@GetMapping
	String get() {
		return "hello java";
	}

}
