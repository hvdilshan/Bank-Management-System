package oop.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RunQuery extends Common {

	/**
	 * 
	 * @param ID
	 * @return String
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static String queryByID(String ID) throws SAXException, IOException, ParserConfigurationException {
		
		NodeList nodeList;
		Element element = null;
		
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(System.getProperty("catalina.base") + "\\wtpwebapps\\OnlineBankingSystem\\WEB-INF\\querys.xml"))
				.getElementsByTagName(Constants.TAG_NAME);
		
		for (int value = 0; value < nodeList.getLength(); value++) {
			element = (Element) nodeList.item(value);
			if (element.getAttribute(Constants.ATTRIB_ID).equals(ID))
				break;
		}
		return element.getTextContent().trim();
		
	}
}
