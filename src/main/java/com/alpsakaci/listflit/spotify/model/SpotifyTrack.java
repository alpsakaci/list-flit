package com.alpsakaci.listflit.spotify.model;

public class SpotifyTrack {

	private String id;
	private String name;
	private String uri;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "SpotifyTrack [id=" + id + ", name=" + name + ", uri=" + uri + "]";
	}

}