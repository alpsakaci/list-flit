package com.alpsakaci.applemusic2spotify;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private SpotifyApiService spotifyApiService;
	
	private String redirectUrl = SpotifyApiConfig.redirectUrl + "?response_type=code" + "&client_id="
			+ SpotifyApiConfig.clientId + "&redirect_uri=" + SpotifyApiConfig.redirectUri + "&scope=user-library-read";

	@GetMapping
	void login(HttpServletResponse res) throws IOException {
		res.setHeader("Location", redirectUrl);
		res.setStatus(302);
	}

	@GetMapping(path = "/callback")
	String callback(HttpServletRequest req) {
		String error = req.getParameter("error");
		String authCode = req.getParameter("code");

		if (error != null) {
			System.out.println(error);
		} else {
			System.out.println(authCode);
			spotifyApiService.getAccessToken(authCode);
		}

		return "spotify-callback";
	}
}
