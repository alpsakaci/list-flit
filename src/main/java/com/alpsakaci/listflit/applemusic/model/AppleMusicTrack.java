package com.alpsakaci.listflit.applemusic.model;

import com.alpsakaci.listflit.common.Track;

public class AppleMusicTrack implements Track {

	private int id;
	private String name;
	private String artist;
	private String album;

	public AppleMusicTrack() {

	}

	public AppleMusicTrack(int id, String name, String artist, String album) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.album = album;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getArtist() {
		return artist;
	}

	@Override
	public String getAlbum() {
		return album;
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", artist=" + artist + ", album=" + album + "]";
	}

}
