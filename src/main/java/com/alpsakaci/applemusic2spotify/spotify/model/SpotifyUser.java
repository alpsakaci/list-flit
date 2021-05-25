package com.alpsakaci.applemusic2spotify.spotify.model;

public class SpotifyUser {

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
