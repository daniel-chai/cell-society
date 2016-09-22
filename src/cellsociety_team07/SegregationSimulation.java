package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Segregation simulation.
 */
public class SegregationSimulation extends Simulation {
	
	public SegregationSimulation(SceneManager sceneManager) {
		super(sceneManager);
	}

	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		
		return simulationScene;
	}
}
