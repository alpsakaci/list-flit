package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.List;

public class LibraryDto {

	private List<AppleMusicTrack> trakcs;
	private List<AppleMusicPlaylist> playlists;

	public List<AppleMusicTrack> getTrakcs() {
		return trakcs;
	}

	public void setTrakcs(List<AppleMusicTrack> trakcs) {
		this.trakcs = trakcs;
	}

	public List<AppleMusicPlaylist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<AppleMusicPlaylist> playlists) {
		this.playlists = playlists;
	}

}
