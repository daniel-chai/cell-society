package cellsociety_team07;

import java.io.File;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import xml.XMLParser;
import xml.DataXMLFactory;
import xml.Data;
import xml.XMLFactoryException;

/**
 * This class represents the DataInput Scene from where XML files are input and parsed.
 */
public class DataInput {
	private SceneManager sceneManager;
	private Scene dataInputScene;
	private Group root;
	private static final String XML_FILES_LOCATION = "data/xml/";
	private static final String XML_SUFFIX = ".xml";
	
	/**
	 * Constructor for DataInput class
	 * @param sceneManager SceneManager currently being used
	 */
	public DataInput(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Returns the DataInput Scene
	 * @param width the width of the Scene
	 * @param height the height of the Scene
	 * @return Scene with the specified attributes
	 */
	public Scene init() {
		root = new Group();
		dataInputScene = new Scene(root, Main.SIZE, Main.SIZE, Color.AZURE);
		
		return dataInputScene;
	}
	
	 public static void main (String[] args) throws XMLFactoryException {
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
