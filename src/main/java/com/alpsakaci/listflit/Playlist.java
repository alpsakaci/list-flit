package com.alpsakaci.listflit;

import java.util.List;
import java.util.Map;


public interface Playlist {

	public Object getId();

	public String getName();
	
	public List<?> getPlaylistItems();

	public Map<?, ?> getItemsMap(List<?> tracks);


}
