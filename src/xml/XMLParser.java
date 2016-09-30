package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import xml.DataXMLFactory;
import xml.XMLFactory;
import xml.XMLFactoryException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


/**
 * Handles parsing XML files by returning the root elements.
 */
public class XMLParser {
    // Keep only one documentBuilder around because it is expensive to make and just need to reset it before every parse
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
    private static final String XML_FILES_LOCATION = "data/xml/";
    private static final String XML_SUFFIX = ".xml";

    /**
     * Gets the root element in an XML file.
     *
     * @param xmlFilename the location of the xmlFile
     * @return the root element in the xmlFile
     */
    public Element getRootElement (String xmlFilename) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFilename);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLParserException(e);
        }
    }

    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private static DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLParserException(e);
        }
    }


    public static void main (String[] args) {
        XMLParser parser = new XMLParser();
        DataXMLFactory factory = new DataXMLFactory();
        File folder = new File(XML_FILES_LOCATION);
        for (File f : folder.listFiles()) {
            if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
                try {
                    Data d = factory.getData(parser.getRootElement(f.getAbsolutePath()));
                    System.out.println(d);
                }
                catch (XMLFactoryException e) {
                    System.err.println("Reading file " + f.getPath());
                    e.printStackTrace();
                }
            }
        }
    }
}
