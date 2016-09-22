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
		
		rows = 10;			// hard-coded for now
		columns = 10;		// hard-coded for now
		
		initGrid();
		
		return simulationScene;
	}
	
	@Override
	protected void initStates() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void calculateNeighbors(Cell cell, int row, int col) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateGrid() {
		// TODO Auto-generated method stub
	}
}
