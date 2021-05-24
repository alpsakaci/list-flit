package com.alpsakaci.applemusic2spotify.applemusic;

public class Track {

	private int id;
	private String name;
	private String artist;
	private String album;

	public Track(int id, String name, String artist, String album) {
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

	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", artist=" + artist + ", album=" + album + "]";
	}

}
