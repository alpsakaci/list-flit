package com.alpsakaci.listflit.migrator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpsakaci.listflit.Playlist;
import com.alpsakaci.listflit.Track;
import com.alpsakaci.listflit.spotify.SpotifyApiService;
import com.alpsakaci.listflit.spotify.model.SpotifyPlaylist;
import com.alpsakaci.listflit.spotify.model.SpotifyTrack;

@Service
public class SpotifyMigrator implements PlaylistMigrator {

	@Autowired
	private SpotifyApiService spotifyApi;

	@Override
	public SpotifyTrack getTrack(Track track) {
		String query = track.getName() + " " + track.getArtist() + " " + track.getAlbum();
		query.replaceAll(" ", "+");

		return spotifyApi.searchTrack(query);
	}

	@Override
	public SpotifyPlaylist createPlaylist(Playlist playlist) {
		return spotifyApi.createPlaylist(playlist.getName());
	}

	@Override
	public void migratePlaylist(Playlist playlist, List<?> tracks) {
		SpotifyPlaylist spotifyPlaylist = this.createPlaylist(playlist);
		List<SpotifyTrack> spotifyTracks = new LinkedList<SpotifyTrack>();
		Map<?, ?> itemsMap = playlist.getItemsMap(tracks);

		for (Object trackId : playlist.getPlaylistItems()) {
			Track appleTrack = (Track) itemsMap.get(trackId);
			SpotifyTrack spotifyTrack = this.getTrack(appleTrack);
			if (spotifyTrack != null) {
				spotifyTracks.add(spotifyTrack);
			}
		}

		spotifyApi.addItemsToPlaylist(spotifyPlaylist, spotifyTracks);
	}

}
