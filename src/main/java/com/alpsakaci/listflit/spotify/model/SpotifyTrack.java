package com.alpsakaci.listflit.spotify.model;

import com.alpsakaci.listflit.Track;

public class SpotifyTrack implements Track {

	private String id;
	private String name;
	private String uri;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
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
	public String getAlbum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "SpotifyTrack [id=" + id + ", name=" + name + ", uri=" + uri + "]";
	}

}
