package cellsociety_team07;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * This class implements the Segregation simulation.
 */
public class SegregationSimulation extends Simulation {
	private static final State stateX = new State("X");
	private static final State stateO = new State("O");
	private static final State stateEmpty = new State("EMPTY");
	
	private double threshold = 0.3; 		// hand-coded for now
	
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
		initStructure();
		
		return simulationScene;
	}
	
	private void initStructure() {
		grid = new Grid(rows, columns);
		initStates();
		initNeighbors();
		displayGrid();
	}
	
	private void initStates() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = new Cell(generateRandomState());
				grid.addCell(cell, row, col);
			}
		}
	}
	
	private State generateRandomState() {
		Random r = new Random();
		int i = r.nextInt(3);
		
		if (i == 0) {
			return stateX;
		}
		if (i == 1) {
			return stateO;
		}
		else {
			return stateEmpty;
		}
	}
	
	private void initNeighbors() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				calculateNeighbors(cell, row, col);
			}
		}
	}
	
	private void calculateNeighbors(Cell cell, int row, int col) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i + row < 0 || i + row >= rows || j + col < 0 || j + col >= columns) {
					continue;
				}
				Cell neighbor = grid.getCell(row + i, col + j);
				cell.getNeighborhood().addNeighbor(neighbor);
			}
		}
	}
	
	private void displayGrid() 
	{
		Node node = grid.generateView(300, 300);
		node.setLayoutX(100);
		node.setLayoutY(100);
		root.getChildren().add(node);
	}
}
