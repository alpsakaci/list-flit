package com.alpsakaci.applemusic2spotify;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.alpsakaci.applemusic2spotify.storage.StorageService;


@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class MusicLibraryController {
	
	private final StorageService storageService;
	
	@Autowired
	public MusicLibraryController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@PostMapping
	@RequestMapping("/uploadLibrary")
	String handleLibraryFileUpload(@RequestParam("libraryFile") MultipartFile file) {
		storageService.store(file);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File("src/main/resources/uploads/Library.xml"));
			return document.getDocumentElement().getNodeName();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file.getName();
	}

	@GetMapping
	String get() {
		return "hello java";
	}
	
}
