# List Flit

You can migrate your playlists from Apple Music to Spotify with List Flit.

### Spotify API Configuration

Register your Client ID and Client using spotifyClientRegistration method under OAuth2Config class.

/src/main/java/com/alpsakaci/listflit/OAuth2Config.java

```java
private ClientRegistration spotifyClientRegistration() {
		return ClientRegistration
				.withRegistrationId("spotify")
				.clientId("your-client-id") // Register Client ID
				.clientSecret("your-client-secret") // Register Client Secret
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
}
```

### Run app with Maven

```
# ./mvnw spring-boot:run
```

### Run app with Docker

Build image

```
# docker build -t alpsakaci/list-flit .
```

Run image

```
# docker run --name list-flit -p 8080:8080 -d alpsakaci/list-flit
```