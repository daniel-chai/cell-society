package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * This class is the abstract superclass for all the different types of simulations. It is meant
 * to be extended. Each particular simulation has different rules, so each particular simulation
 * will extend this Simulation superclass and implement its own rules.
 */
public abstract class Simulation {
	protected SceneManager sceneManager;
	protected Scene simulationScene;
	protected Group root;
	
	protected Grid grid;
	protected int rows;
	protected int columns;
	
	protected Simulation(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	protected void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Back to Menu", 20, 20, 150, 20, 15);
		menuButton.setOnAction(e -> sceneManager.goToMenuScene(sceneManager));
		root.getChildren().add(menuButton);
	}
	
	protected void addStepButton() {
		Button stepButton = UIGenerator.createButton("Show Next Step", 200, 20, 150, 20, 15);
		stepButton.setOnAction(e -> updateGrid());
		root.getChildren().add(stepButton);
	}
	
	protected abstract void updateGrid();
}
