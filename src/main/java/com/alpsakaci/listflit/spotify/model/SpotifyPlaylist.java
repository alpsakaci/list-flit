package com.alpsakaci.listflit.spotify.model;

import java.util.List;
import java.util.Map;

import com.alpsakaci.listflit.common.Playlist;

public class SpotifyPlaylist implements Playlist {

	private String id;
	private String href;
	private String name;

	@Override
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

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<?> getPlaylistItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<?, ?> getItemsMap(List<?> tracks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "SpotifyPlaylist [id=" + id + ", href=" + href + ", name=" + name + "]";
	}

}
