package com.alpsakaci.applemusic2spotify.spotify.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class SpotifyUser {

	@JsonAlias(value = "display_name")
	private String displayName;
	private SpotifyImage[] images;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public SpotifyImage[] getImages() {
		return images;
	}

	public void setImages(SpotifyImage[] images) {
		this.images = images;
	}

}
