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

	private Node getTracksNode(MultipartFile file) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file.getInputStream());
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			Node rootDict = nodes.item(1);
			NodeList dictNodes = rootDict.getChildNodes();

			for (int i = 0; i < dictNodes.getLength(); i++) {
				Node currentNode = dictNodes.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getTextContent().equals("Tracks")) {
					return dictNodes.item(i + 2);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Track createTrack(Node trackNode) {
		int id = -1;
		String name = "";
		String artist = "";
		String album = "";
		NodeList trackInfo = trackNode.getChildNodes();

		for (int i = 0; i < trackInfo.getLength(); i++) {
			Node field = trackInfo.item(i);

			if (field.getNodeType() == Node.ELEMENT_NODE) {
				switch (field.getTextContent()) {
				case "Track ID":
					id = Integer.parseInt(trackInfo.item(i + 1).getTextContent());
					break;
				case "Name":
					name = trackInfo.item(i + 1).getTextContent();
					break;
				case "Artist":
					artist = trackInfo.item(i + 1).getTextContent();
					break;
				case "Album":
					album = trackInfo.item(i + 1).getTextContent();
					break;
				}
			}
		}

		Track track = new Track(id, name, artist, album);
		System.out.println(track.toString());

		return track;
	}

	public List<Track> getTrackList(MultipartFile file) {
		NodeList trackNodes = getTracksNode(file).getChildNodes();
		List<Track> tracks = new ArrayList<Track>();

		for (int i = 0; i < trackNodes.getLength(); i++) {
			Node currentNode = trackNodes.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName().equals("dict")) {
				tracks.add(createTrack(currentNode));
			}
		}

		return tracks;
	}

}
