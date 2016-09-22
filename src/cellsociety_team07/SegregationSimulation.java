package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Segregation simulation.
 */
public class SegregationSimulation extends Simulation {
	private static final String stateX = "X";
	private static final String stateO = "O";
	
	public SegregationSimulation(SceneManager sceneManager) {
		super(sceneManager);
	}

	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		
		rows = 10;			// hard-coded for now
		columns = 10;		// hard-coded for now
		initGrid();
		
		return simulationScene;
	}
	
	private void initGrid() {
		grid = new Grid(rows, columns);
		initStates();
		initNeighbors();
		displayGrid();
	}
	
	private void initStates() {
		// initialize the states of the Cells in the grid
	}
	
	private void initNeighbors() {
		// initialize the neighbors of the Cells in the grid
	}
	
	private void displayGrid() {
		// display the grid on the Scene
	}
}
