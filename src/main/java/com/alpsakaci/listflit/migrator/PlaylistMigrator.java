package com.alpsakaci.listflit.migrator;

import java.util.List;

import com.alpsakaci.listflit.Playlist;
import com.alpsakaci.listflit.Track;

public interface PlaylistMigrator {

	public Object getTrack(Track track);

	public Object createPlaylist(Playlist playlist);

	public void migratePlaylist(Playlist playlist, List<?> tracks);

}
