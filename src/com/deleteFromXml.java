package com;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class deleteFromXml {
	
	public static void deleteFromXml(String email) {
		try {
			File xmlFile = new File("C:\\Users\\User\\eclipse\\jee-2020-12\\eclipse\\eclipse-workspace\\project4develabs\\WebContent\\data.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			
			NodeList emails = document.getElementsByTagName("email");
			for(int i=0; i<emails.getLength(); i++) {
				//System.out.println(emails.item(i).getTextContent());
				//System.out.println("--------------------");
				if(emails.item(i).getTextContent().equals(email)) {
					System.out.println("Korisnika sa email adresom " + emails.item(i).getTextContent() + " treba obrisati!");
					Node userNode = emails.item(i).getParentNode();
					//System.out.println(userNode.getNodeName());
					Node countryNode = userNode.getParentNode();
					//System.out.println(countryNode.getNodeName());
					countryNode.removeChild(userNode);
				}
			}
			
			Transformer tFormer = TransformerFactory.newInstance().newTransformer();
			tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
			Source source = new DOMSource(document);
			Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);
			System.out.println("Uspjesno obrisano!\n-------------------------");
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
}

// src\\app\\data.xml