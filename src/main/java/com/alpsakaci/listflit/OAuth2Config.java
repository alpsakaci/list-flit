package com.alpsakaci.listflit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class OAuth2Config extends WebSecurityConfigurerAdapter {
	
	private static final String[] PUBLIC_MATCHERS = {
            "/",
            "/css/**",
            "/js/**",
            "/img/**",
    };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.cors().and().csrf().disable()
			.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated()
			.and()
			.oauth2Login();
		// @formatter:on
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.spotifyClientRegistration());
	}

	private ClientRegistration spotifyClientRegistration() {
		// @formatter:off
		return ClientRegistration
				.withRegistrationId("spotify")
				.clientId("your-client-id")
				.clientSecret("your-client-secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
				.scope("user-library-read", "playlist-modify-public", "playlist-modify-private")
				.authorizationUri("https://accounts.spotify.com/authorize")
				.tokenUri("https://accounts.spotify.com/api/token")
				.userInfoUri("https://api.spotify.com/v1/me")
				.userNameAttributeName("display_name")
				.clientName("spotify")
				.build();
		// @formatter:on
	}

}
