package com.alpsakaci.applemusic2spotify;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	
	
	private String redirectUrl = "https://accounts.spotify.com/authorize" 
			+ "?response_type=code"
			+ "&client_id="
			+ "cf7105826f924525a5e1ee739e0b1a98"
//			+ "&scope="
//			+ "req_scopes"
			+ "&redirect_uri="
			+ "http://localhost:8080/auth/callback";
	
	@GetMapping
	void login(HttpServletResponse res) throws IOException {
		res.setHeader("Location", redirectUrl);
	    res.setStatus(302);
	}
}
