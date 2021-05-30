package com.alpsakaci.applemusic2spotify.applemusic.model;

import java.util.List;

public class AppleMusicLibrary {

	private List<AppleMusicTrack> tracks;
	private List<AppleMusicPlaylist> playlists;

	public AppleMusicLibrary(List<AppleMusicTrack> tracks, List<AppleMusicPlaylist> playlists) {
		this.tracks = tracks;
		this.playlists = playlists;
	}

	public List<AppleMusicTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<AppleMusicTrack> trakcs) {
		this.tracks = trakcs;
	}

	public List<AppleMusicPlaylist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<AppleMusicPlaylist> playlists) {
		this.playlists = playlists;
	}

}
