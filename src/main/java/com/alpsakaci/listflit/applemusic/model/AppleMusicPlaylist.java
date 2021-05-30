package com.alpsakaci.listflit.applemusic.model;

import java.util.List;
import java.util.LinkedList;

public class AppleMusicPlaylist {

	private int id;
	private String name;
	private List<Integer> playlistItems = new LinkedList<Integer>();

	public AppleMusicPlaylist(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addItem(int trackId) {
		playlistItems.add(trackId);
	}

	public List<Integer> getPlaylistItems() {
		return playlistItems;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", itemCount=" + playlistItems.size() + "]";
	}

}
