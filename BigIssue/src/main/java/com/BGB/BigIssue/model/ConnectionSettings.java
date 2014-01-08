package com.BGB.BigIssue.model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ConnectionSettings {

	private String url;
	private String pass;
	private String name;
	private boolean read = false;

	private void read(){

		if(!read){

			try {

				File fXmlFile = new File("src/main/resources/connectionSettings.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("database");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						url = eElement.getElementsByTagName("url").item(0).getTextContent();
						name = eElement.getElementsByTagName("u").item(0).getTextContent();
						pass = eElement.getElementsByTagName("p").item(0).getTextContent();

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				read= true;
			}
		}
	}
	
	public String getServ(){
		read();
		return this.url;
	}
	
	public String getName(){
		read();
		return this.name;
	}
	
	public String getPass(){
		read();
		return this.pass;
	}


}
