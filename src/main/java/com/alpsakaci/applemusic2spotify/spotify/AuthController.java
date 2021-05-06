package com.alpsakaci.applemusic2spotify.spotify;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
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
	void callback(HttpServletRequest req, HttpServletResponse res) {
		String error = req.getParameter("error");
		String authCode = req.getParameter("code");

		if (error != null) {
			System.out.println(error);
			res.setHeader("Location", "/");
			res.setStatus(302);
		} else {
			Map<String, String> token = spotifyApiService.getAccessToken(authCode);
			
			Cookie accessToken = new Cookie("access_token", token.get("access_token"));
			accessToken.setMaxAge(3600);
			
			Cookie refreshToken = new Cookie("refesh_token", token.get("refresh_token"));
			refreshToken.setMaxAge(3600);
			
			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			res.setHeader("Location", "/library");
			res.setStatus(302);
		}

	}
	
}
