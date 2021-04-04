package com.alpsakaci.applemusic2spotify.storage;

import org.springframework.context.annotation.Configuration;

@Configuration(value = "storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "src/main/resources/uploads";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
