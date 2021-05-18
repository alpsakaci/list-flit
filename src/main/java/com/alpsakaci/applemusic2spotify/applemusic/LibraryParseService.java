package com.alpsakaci.applemusic2spotify.applemusic;

import java.util.ArrayList;
import java.util.LinkedList;
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

	private Node getPlaylistsNode(MultipartFile file) {
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
				if (currentNode.getNodeType() == Node.ELEMENT_NODE
						&& currentNode.getTextContent().equals("Playlists")) {
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

		return track;
	}

	private Playlist createPlaylist(Node playlistsNode) {
		int id = -1;
		String name = "";
		NodeList playlists = playlistsNode.getChildNodes();
		NodeList playlistItems = null;

		for (int i = 0; i < playlists.getLength(); i++) {
			Node field = playlists.item(i);

			if (field.getNodeType() == Node.ELEMENT_NODE) {
				switch (field.getTextContent()) {
				case "Playlist ID":
					id = Integer.parseInt(playlists.item(i + 1).getTextContent());
					break;
				case "Name":
					name = playlists.item(i + 1).getTextContent();
					break;
				case "Playlist Items":
					playlistItems = playlists.item(i + 2).getChildNodes();
					break;
				}
			}
		}

		Playlist playlist = new Playlist(id, name);

		for (int i = 0; i < playlistItems.getLength(); i++) {
			NodeList dict = playlistItems.item(i).getChildNodes();

			for (int j = 0; j < dict.getLength(); j++) {
				Node field = dict.item(j);
				if (field.getNodeType() == Node.ELEMENT_NODE) {
					switch (field.getTextContent()) {
					case "Track ID":
						int trackId = Integer.parseInt(dict.item(j + 1).getTextContent());
						playlist.addItem(trackId);
						break;
					}
				}
			}

		}

		return playlist;
	}

	public List<Playlist> getPlaylists(MultipartFile file) {
		NodeList playlistNodes = getPlaylistsNode(file).getChildNodes();
		List<Playlist> playlists = new LinkedList<Playlist>();

		for (int i = 0; i < playlistNodes.getLength(); i++) {
			Node currentNode = playlistNodes.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName().equals("dict")) {
				playlists.add(createPlaylist(currentNode));
			}
		}

		return playlists;
	}

	public List<Track> getTrackList(MultipartFile file) {
		NodeList trackNodes = getTracksNode(file).getChildNodes();
		// TODO: impl appropriate data type
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
