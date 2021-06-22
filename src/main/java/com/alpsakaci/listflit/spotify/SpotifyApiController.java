package com.alpsakaci.listflit.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpsakaci.listflit.spotify.model.SpotifyUser;

@RestController
public class SpotifyApiController {

	@Autowired
	private SpotifyApiService spotifyApi;

	@GetMapping("/me")
	SpotifyUser me() {
		return spotifyApi.me();
	}

}
