package com.alpsakaci.applemusic2spotify.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpsakaci.applemusic2spotify.spotify.model.SpotifyUser;

@RestController
public class SpotifyApiController {

	@Autowired
	private SpotifyApiService apiService;

	@GetMapping("/me")
	SpotifyUser me() {
		return apiService.me();
	}

}
