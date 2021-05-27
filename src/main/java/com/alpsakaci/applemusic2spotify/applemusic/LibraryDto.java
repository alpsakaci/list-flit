package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.List;

public class LibraryDto {

	private List<AppleMusicTrack> tracks;
	private List<AppleMusicPlaylist> playlists;

	public LibraryDto(List<AppleMusicTrack> tracks, List<AppleMusicPlaylist> playlists) {
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
