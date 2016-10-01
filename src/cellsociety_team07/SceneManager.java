package cellsociety_team07;

import java.awt.Point;

import javafx.scene.Scene;
import javafx.stage.Stage;
import xml.FireData;
import xml.LifeData;
import xml.PredData;
import xml.SegregationData;
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
	private static final String SEGREGATION = "Schelling's Model Of Segregation";
	private static final String PREDATOR_PREY = "Predator-Prey";
	private static final String FIRE = "Spreading Of Fire";
	private static final String GAME_OF_LIFE = "Conway's Game of Life";
	
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
		SegregationData data = (SegregationData) DataInput.getInputData(SEGREGATION);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		double threshold = data.getMyThreshold();
		
		SegregationSimulation simulation = new SegregationSimulation(sceneManager, rows, cols, threshold);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	/**
	 * Sets the scene to be the Predator-Prey simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToPredatorPreyScene(SceneManager sceneManager) {
		PredData data = (PredData) DataInput.getInputData(PREDATOR_PREY);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		int fishTurnsToBreed = data.getMyFishBreed();
		int sharkTurnsToBreed = data.getMySharkBreed();
		
		PredatorPreySimulation simulation = new PredatorPreySimulation(sceneManager, rows, cols, fishTurnsToBreed, sharkTurnsToBreed);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}

	/**
	 * Sets the scene to be the Fire simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToFireScene(SceneManager sceneManager) {
		FireData data = (FireData) DataInput.getInputData(FIRE);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		double probCatch = data.getMyProbCatch();
		Point startCell = data.getMyInitialFire();
		
		FireSimulation simulation = new FireSimulation(sceneManager, rows, cols, probCatch, startCell);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	/**
	 * Sets the scene to be the Game-of-Life simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToGameOfLifeScene(SceneManager sceneManager) {
		LifeData data = (LifeData) DataInput.getInputData(GAME_OF_LIFE);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		
		GameOfLifeSimulation simulation = new GameOfLifeSimulation(sceneManager, rows, cols);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
}
