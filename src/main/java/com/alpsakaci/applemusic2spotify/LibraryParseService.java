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
	
	public List<Node> getTracks(MultipartFile file) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file.getInputStream());
			Element root = document.getDocumentElement();
			root.normalize();
			NodeList nodes = root.getChildNodes();
			Node rootDict = nodes.item(1);
			NodeList dictNodes = rootDict.getChildNodes();
			List<Node> tracks = new ArrayList<Node>();
			
			for (int i=0; i<dictNodes.getLength(); i++) {
				Node currentNode = dictNodes.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName() == "dict") {
					NodeList trackList = currentNode.getChildNodes();
					for (int j=0 ; j<trackList.getLength(); j++) {
						Node temp = trackList.item(j);
						if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName() == "dict") {
							tracks.add(temp);
							System.out.println(temp.getTextContent());
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
