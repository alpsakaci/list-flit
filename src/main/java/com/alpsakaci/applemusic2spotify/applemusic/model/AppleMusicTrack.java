package com.alpsakaci.applemusic2spotify.applemusic.model;

public class AppleMusicTrack {

	private int id;
	private String name;
	private String artist;
	private String album;

	public AppleMusicTrack(int id, String name, String artist, String album) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.album = album;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String buildQueryString() {
		String query = name + " " + artist + " " + album;
		return query.replaceAll(" ", "+");
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", artist=" + artist + ", album=" + album + "]";
	}

}
