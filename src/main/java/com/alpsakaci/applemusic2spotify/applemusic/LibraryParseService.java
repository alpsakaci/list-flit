package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alpsakaci.applemusic2spotify.applemusic.model.AppleMusicLibrary;
import com.alpsakaci.applemusic2spotify.applemusic.model.AppleMusicPlaylist;
import com.alpsakaci.applemusic2spotify.applemusic.model.AppleMusicTrack;

public interface LibraryParseService {

	List<AppleMusicPlaylist> getPlaylists(MultipartFile file);

	List<AppleMusicTrack> getTracks(MultipartFile file);

	AppleMusicLibrary parseLibrary(MultipartFile file);

	Map<Integer, AppleMusicTrack> convertListToMap(List<AppleMusicTrack> trackList);

}
