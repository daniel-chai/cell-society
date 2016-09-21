package cellsociety_team07;

import javafx.scene.Scene;
import javafx.stage.Stage;

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
		Scene menuScene = menu.init(Main.SIZE, Main.SIZE);
		stage.setScene(menuScene);
	}
	
	/**
	 * Sets the scene to be the DataInputScene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToDataInputScene(SceneManager sceneManager) {
		DataInput dataInput = new DataInput(sceneManager);
		Scene dataInputScene = dataInput.init(Main.SIZE, Main.SIZE);
		stage.setScene(dataInputScene);
	}
	
	/**
	 * Sets the scene to be the Simulation Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToSimulationScene(SceneManager sceneManager) {
		
	}
}
