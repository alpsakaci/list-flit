package com.alpsakaci.listflit.spotify.model;

public class SpotifyPlaylist {

	private String id;
	private String href;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SpotifyPlaylist [id=" + id + ", href=" + href + ", name=" + name + "]";
	}

}