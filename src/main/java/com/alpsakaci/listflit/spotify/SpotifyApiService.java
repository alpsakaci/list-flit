package com.alpsakaci.listflit.spotify;

import java.util.List;

import com.alpsakaci.listflit.applemusic.model.PlaylistItemsDto;
import com.alpsakaci.listflit.spotify.model.SpotifyPlaylist;
import com.alpsakaci.listflit.spotify.model.SpotifyTrack;
import com.alpsakaci.listflit.spotify.model.SpotifyUser;

public interface SpotifyApiService {

	public SpotifyUser me();

	public SpotifyTrack searchTrack(String trackName);

	public SpotifyPlaylist createPlaylist(String name);

	public void addItemsToPlaylist(SpotifyPlaylist playlist, List<SpotifyTrack> tracks);

	public void importPlaylist(PlaylistItemsDto applePlaylist);

}
