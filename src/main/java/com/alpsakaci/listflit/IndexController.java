package com.alpsakaci.listflit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alpsakaci.listflit.applemusic.LibraryParseService;
import com.alpsakaci.listflit.applemusic.model.AppleMusicLibrary;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private LibraryParseService libraryParseService;

	@GetMapping
	String index(Authentication auth) {

		if (auth == null) {
			return "login";
		}

		return "index";
	}
	
	@PostMapping
	String handleFormSubmission(@RequestParam("libraryFile") MultipartFile file, Model model) {
		AppleMusicLibrary library = libraryParseService.parseLibrary(file);
		model.addAttribute("playlists", library.getPlaylists());
		model.addAttribute("tracks", library.getTracks());
		
		return "library";
	}

}
