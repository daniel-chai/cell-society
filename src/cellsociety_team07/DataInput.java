package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class represents the DataInput Scene from where XML files are input and parsed.
 */
public class DataInput {
	private SceneManager sceneManager;
	private Scene dataInputScene;
	private Group root;
	
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
	public Scene init(int width, int height) {
		root = new Group();
		dataInputScene = new Scene(root, width, height, Color.AZURE);
		
		return dataInputScene;
	}
}
