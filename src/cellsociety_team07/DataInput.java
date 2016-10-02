package cellsociety_team07;

import java.io.File;

import xml.Data;
import xml.DataXMLFactory;
import xml.XMLFactoryException;
import xml.XMLParser;

public class DataInput {
	private static final String XML_FILES_LOCATION = "data/xml/";
	private static final String XML_SUFFIX = ".xml";
	
	public static Data getInputData(String title) {
		XMLParser parser = new XMLParser();
	    DataXMLFactory factory = new DataXMLFactory();
	    File folder = new File(XML_FILES_LOCATION);
	    
		for (File f : folder.listFiles()) {
			if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
				try {
					Data d = factory.getData(parser.getRootElement(f.getAbsolutePath()));
					if (d.getMyTitle().equals(title)) {
						return d;
					}
				} catch (XMLFactoryException e) {
					System.err.println("Reading file " + f.getPath());
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
}
