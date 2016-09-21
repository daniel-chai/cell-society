package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class represents the Menu Scene from where a simulation can be started.
 */
public class Menu {
	private SceneManager sceneManager;
	private Scene menuScene;
	private Group root;
	
	/**
	 * Constructor for Menu class
	 * @param sceneManager SceneManager currently being used
	 */
	public Menu(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Returns the Menu Scene
	 * @param width the width of the Scene
	 * @param height the height of the Scene
	 * @return Scene with the specified attributes
	 */
	public Scene init(int width, int height) {
		root = new Group();
		menuScene = new Scene(root, width, height, Color.AZURE);
		
		return menuScene;
	}
}
