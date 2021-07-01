package com;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class writeToXml {
	
	public static void writeNodeToXml(User u) {
		try {
			//promijenitiPutanju
			File xmlFile = new File("C:\\Users\\User\\eclipse\\jee-2020-12\\eclipse\\eclipse-workspace\\project4develabs\\WebContent\\data.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			
			Element documentElement = document.getDocumentElement();
			
			NodeList continents = document.getElementsByTagName("continent");
			for (int i =0; i<continents.getLength(); i++) {
				Node continent = continents.item(i);
				String nameOfContinent = continent.getAttributes().getNamedItem("name").getNodeValue();
				//System.out.println(nameOfContinent);
				
				if(u.getContinent().equals(nameOfContinent)) {
					
					NodeList countries = document.getElementsByTagName("country");
					for(int j=0; j<countries.getLength(); j++) {
						Node country = countries.item(j);
						String nameOfCountry = country.getAttributes().getNamedItem("name").getNodeValue();
						//System.out.println(nameOfCountry);
						if(nameOfCountry.equals(u.getCountry())) {
							//System.out.println(nameOfCountry);
							
							Element userElement = document.createElement("user");
							
							Element firstNameElement = document.createElement("first_name");
							Text firstNameText = document.createTextNode(u.getFirstname());
							firstNameElement.appendChild(firstNameText);
							
							Element lastNameElement = document.createElement("last_name");
							Text lastNameText = document.createTextNode(u.getLastname());
							lastNameElement.appendChild(lastNameText);
							
							Element addressElement= document.createElement("address");
							Text addressText = document.createTextNode(u.getAddress());
							addressElement.appendChild(addressText);
							
							Element cityElement = document.createElement("city");
							Text cityText = document.createTextNode(u.getCity());
							cityElement.appendChild(cityText);
							
							Element emailElement = document.createElement("email");
							Text emailText = document.createTextNode(u.getEmail());
							emailElement.appendChild(emailText);
							
							Element passwordElement = document.createElement("password");
							Text passwordText = document.createTextNode(u.getPassword());
							passwordElement.appendChild(passwordText);
							
							userElement.appendChild(firstNameElement);
							userElement.appendChild(lastNameElement);
							userElement.appendChild(addressElement);
							userElement.appendChild(cityElement);
							userElement.appendChild(emailElement);
							userElement.appendChild(passwordElement);
							
							country.appendChild(userElement);
						}
					}
				}
				
				
				
			}
			Transformer tFormer = TransformerFactory.newInstance().newTransformer();
			tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
			Source source = new DOMSource(document);
			Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);
			
			
			System.out.println("Uspjesno upisano!\n-------------------------");
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
