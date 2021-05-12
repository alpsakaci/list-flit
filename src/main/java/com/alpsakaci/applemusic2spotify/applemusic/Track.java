package com.alpsakaci.applemusic2spotify.applemusic;

public class Track {

	private int id;
	private String name;
	private String artist;
	private String album;

	public Track() {
	}

	public Track(int id, String name, String artist, String album) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.album = album;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", artist=" + artist + ", album=" + album + "]";
	}

}
