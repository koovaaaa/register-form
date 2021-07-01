package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


@WebServlet("/shoutServlet")
public class shoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String password = request.getParameter("password");
		String fullNameOfCountry = "";
		String nameOfContinent = "";	
			
		switch(country) {
		case "us":
			fullNameOfCountry = "United States";
			nameOfContinent = "North America";
			break;
		case "ca":
			fullNameOfCountry = "Canada";
			nameOfContinent = "North America";
			break;
		case "uk":
			fullNameOfCountry = "United Kingdom";
			nameOfContinent = "Europe";
			break;
		case "fra":
			fullNameOfCountry = "France";
			nameOfContinent = "Europe";
			break;
		case "ger":
			fullNameOfCountry = "Germany";
			nameOfContinent = "Europe";
			break;
		case "ch":
			fullNameOfCountry = "China";
			nameOfContinent = "Asia";
			break;
		case "ja":
			fullNameOfCountry = "Japan";
			nameOfContinent = "Asia";
			break;
		default:
			fullNameOfCountry = "Wrong name!";
			break;
		}
		
		User user = new User(firstname, lastname, address, city, fullNameOfCountry, nameOfContinent, email, password);
		//System.out.println("Fullname: " + user.getFirstname() + " " + user.getLastname() + "\nAddress: "+ user.getAddress() + "\nCity: " + user.getCity() + "\nCountry: " + user.getCountry() + "\nE-mail: " + user.getEmail());
		writeToXml.writeNodeToXml(user);
		

	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    response.setDateHeader("Expires", 0);
	    
		response.sendRedirect("index.html");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		String email = req.getParameter("email");
		deleteFromXml.deleteFromXml(email);
		
	    resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    resp.setDateHeader("Expires", 0);
		
		resp.sendRedirect("index.html");
	}
}
