package com.alpsakaci.listflit.applemusic.model;

import java.util.List;

public class PlaylistItemsDto {

	private List<AppleMusicTrack> tracks;
	private AppleMusicPlaylist playlist;

	public List<AppleMusicTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<AppleMusicTrack> tracks) {
		this.tracks = tracks;
	}

	public AppleMusicPlaylist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(AppleMusicPlaylist playlist) {
		this.playlist = playlist;
	}

}
