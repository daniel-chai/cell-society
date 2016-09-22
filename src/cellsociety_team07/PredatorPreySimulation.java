package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Predator-Prey simulation.
 */
public class PredatorPreySimulation extends Simulation {

	public PredatorPreySimulation(SceneManager sceneManager) {
		super(sceneManager);
	}
	
	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		
		return simulationScene;
	}
}
