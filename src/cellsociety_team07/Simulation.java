package cellsociety_team07;

import structure.Structure;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This class is the abstract superclass for all the different types of simulations. It is meant
 * to be extended. Each particular simulation has different rules, so each particular simulation
 * will extend this Simulation superclass and implement its own rules.
 */
public abstract class Simulation {
	protected SceneManager sceneManager;
	protected Scene simulationScene;
	protected Group root;
	
	protected Structure grid;
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
		root.getChildren().add(stepButton);
		
		stepButton.setOnAction(event -> testGrid());
	}
	
	public void testGrid()
	{
		int randI = (int)(Math.random() * grid.getHeight());
		int randJ = (int)(Math.random() * grid.getWidth());
		
		grid.getCell(randI, randJ).getView().setColor(Color.YELLOW);
	}
}
