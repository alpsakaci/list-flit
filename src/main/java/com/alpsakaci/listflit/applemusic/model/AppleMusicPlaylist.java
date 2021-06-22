package com.alpsakaci.listflit.applemusic.model;

import java.util.List;
import java.util.Map;

import com.alpsakaci.listflit.Playlist;
import com.alpsakaci.listflit.Track;

import java.util.HashMap;
import java.util.LinkedList;

public class AppleMusicPlaylist implements Playlist {

	private int id;
	private String name;
	private List<Integer> playlistItems = new LinkedList<Integer>();

	public AppleMusicPlaylist(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void addItem(int trackId) {
		playlistItems.add(trackId);
	}

	@Override
	public List<Integer> getPlaylistItems() {
		return playlistItems;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", itemCount=" + playlistItems.size() + "]";
	}

	@Override
	public Map<Integer, Track> getItemsMap(List<?> trackList) {
		Map<Integer, Track> tracksMap = new HashMap<Integer, Track>();
		@SuppressWarnings("unchecked")
		List<Track> tracks = (List<Track>) trackList;
		
		for (Track track : tracks) {
			tracksMap.put((Integer) track.getId(), track);
		}

		return tracksMap;
	}

}
