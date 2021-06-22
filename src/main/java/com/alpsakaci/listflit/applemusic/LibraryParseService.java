package com.alpsakaci.listflit.applemusic;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alpsakaci.listflit.applemusic.model.AppleMusicLibrary;
import com.alpsakaci.listflit.applemusic.model.AppleMusicPlaylist;
import com.alpsakaci.listflit.applemusic.model.AppleMusicTrack;

public interface LibraryParseService {

	List<AppleMusicPlaylist> getPlaylists(MultipartFile file);

	List<AppleMusicTrack> getTracks(MultipartFile file);

	AppleMusicLibrary parseLibrary(MultipartFile file);

}
