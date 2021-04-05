package com.alpsakaci.applemusic2spotify;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class LibraryParseService {
	
	private Track createTrack(Node trackNode) {
		int id = -1;
		String name = "";
		String artist = "";
		String album = "";
		NodeList trackInfo = trackNode.getChildNodes();
		
		for (int i=0; i<trackInfo.getLength(); i++) {
			Node field = trackInfo.item(i);
			
//			TODO: convert to switch case
			if (field.getNodeType() == Node.ELEMENT_NODE && field.getTextContent().equals("Track ID")) {
				id = Integer.parseInt(trackInfo.item(i+1).getTextContent());
			} else if (field.getNodeType() == Node.ELEMENT_NODE && field.getTextContent().equals("Name")) {
				name = trackInfo.item(i+1).getTextContent();
			} else if (field.getNodeType() == Node.ELEMENT_NODE && field.getTextContent().equals("Artist")) {
				artist = trackInfo.item(i+1).getTextContent();
			} else if (field.getNodeType() == Node.ELEMENT_NODE && field.getTextContent().equals("Album")) {
				album = trackInfo.item(i+1).getTextContent();
			}
		}
		
		Track track = new Track(id, name, artist, album);
		System.out.println(track.toString());
		
		return track;
	}
	
	public List<Track> getTracks(MultipartFile file) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file.getInputStream());
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			Node rootDict = nodes.item(1);
			NodeList dictNodes = rootDict.getChildNodes();
			List<Track> tracks = new ArrayList<Track>();
			
			for (int i=0; i<dictNodes.getLength(); i++) {
				Node currentNode = dictNodes.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName() == "dict") {
					NodeList trackList = currentNode.getChildNodes();
					for (int j=0 ; j<trackList.getLength(); j++) {
						Node temp = trackList.item(j);
						if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName() == "dict") {
							tracks.add(createTrack(temp));
						}
					}
				}
			}
			
			return tracks;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
