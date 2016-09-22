package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Fire simulation.
 */
public class FireSimulation extends Simulation {

	public FireSimulation(SceneManager sceneManager) {
		super(sceneManager);
	}
	
	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		
		return simulationScene;
	}

	@Override
	protected void updateGrid() {
		// TODO Auto-generated method stub
	}
}
