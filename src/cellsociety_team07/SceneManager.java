package cellsociety_team07;

import java.awt.Point;
import java.io.File;

import javafx.scene.Scene;
import javafx.stage.Stage;
import xml.Data;
import xml.DataXMLFactory;
import xml.FireData;
import xml.LifeData;
import xml.PredData;
import xml.SegregationData;
import xml.XMLFactoryException;
import xml.XMLParser;

/**
 * This class handles all the scene changes. In the whole application, there is only one Stage that is 
 * used. That is why this class takes in the primaryStage as a parameter in the constructor. All the 
 * scene changes are done on this Stage. Because this Stage is a saved state across the application, 
 * the same SceneManager object has to be used throughout the application. The implication is that 
 * every public method this class provides takes in a SceneManager object as a parameter. Each of these 
 * public methods goes to a separate Scene. So from the Scene classes, to instigate a scene switch, any 
 * one of the provided public methods can be called with the current SceneManager object passed in as 
 * a parameter.
 */
public class SceneManager {
	private static final String XML_FILES_LOCATION = "data/xml/";
	private static final String XML_SUFFIX = ".xml";
	
	private Stage stage;
	
	/**
	 * Constructor for SceneManager class
	 * @param primaryStage the Stage that this SceneManager uses
	 */
	public SceneManager(Stage primaryStage) {
		this.stage = primaryStage;
		stage.show();
	}
	
	/**
	 * Sets the scene to be the Menu Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToMenuScene(SceneManager sceneManager) {
		Menu menu = new Menu(sceneManager);
		Scene menuScene = menu.init();
		stage.setScene(menuScene);
	}
	
	/**
	 * Sets the scene to be the Segregation simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToSegregationScene(SceneManager sceneManager) {
		SegregationData data = (SegregationData) getInputData("Schelling's Model Of Segregation");
		
		int rows = Integer.parseInt(data.getMyNumRows());
		int cols = Integer.parseInt(data.getMyNumCols());
		double threshold = Double.parseDouble(data.getMyThreshold());
		
		SegregationSimulation simulation = new SegregationSimulation(sceneManager, rows, cols, threshold);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	/**
	 * Sets the scene to be the Predator-Prey simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToPredatorPreyScene(SceneManager sceneManager) {
		PredData data = (PredData) getInputData("Predator-Prey");
		
		int rows = Integer.parseInt(data.getMyNumRows());
		int cols = Integer.parseInt(data.getMyNumCols());
		int fishTurnsToBreed = Integer.parseInt(data.getMyFishBreed());
		int sharkTurnsToBreed = Integer.parseInt(data.getMySharkBreed());
		
		PredatorPreySimulation simulation = new PredatorPreySimulation(sceneManager, rows, cols, fishTurnsToBreed, sharkTurnsToBreed);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}

	/**
	 * Sets the scene to be the Fire simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToFireScene(SceneManager sceneManager) {
		FireData data = (FireData) getInputData("Spreading Of Fire");
		
		int rows = Integer.parseInt(data.getMyNumRows());
		int cols = Integer.parseInt(data.getMyNumCols());
		double probCatch = Double.parseDouble(data.getMyProbCatch());
		
		String startCellString = data.getMyInitialFire();
		int index = startCellString.indexOf(',');
		Point startCell = new Point(Integer.parseInt(startCellString.substring(0, index)), Integer.parseInt(startCellString.substring(index + 2)));
		
		FireSimulation simulation = new FireSimulation(sceneManager, rows, cols, probCatch, startCell);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	/**
	 * Sets the scene to be the Game-of-Life simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToGameOfLifeScene(SceneManager sceneManager) {
		LifeData data = (LifeData) getInputData("Conway's Game of Life");
		
		int rows = Integer.parseInt(data.getMyNumRows());
		int cols = Integer.parseInt(data.getMyNumCols());
		
		GameOfLifeSimulation simulation = new GameOfLifeSimulation(sceneManager, rows, cols);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	private Data getInputData(String title) {
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
