package com.alpsakaci.listflit;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping
	String index(Authentication auth) {

		if (auth == null) {
			return "login";
		}

		return "index";
	}

}
