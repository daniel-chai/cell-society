package cellsociety_team07;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
	
	protected Group gridUI;
	protected Map<State, Color> colorMap;
	
	protected State[][] nextState;
	
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
	
	protected void displayGrid() {
		gridUI = new Group();
		root.getChildren().add(gridUI);
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {

				Cell cell = grid.getCell(row, col);
				Rectangle rect = cell.getRectangle();
				
				for (State s : colorMap.keySet()) {
					if (cell.getState().equals(s)) {
						rect.setFill(colorMap.get(s));
					}
				}
				
				rect.setX(100 + col * (Cell.CELL_SIZE + 1));
				rect.setY(100 + row * (Cell.CELL_SIZE + 1));
				
				gridUI.getChildren().add(rect);
			}
		}
	}
	
	protected void initGrid() {
		grid = new Grid(rows, columns);		
		initStates();
		initNeighbors();
		
		displayGrid();
	}
	
	protected abstract void initStates();	
	
	protected void initNeighbors() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				calculateNeighbors(cell, row, col);
			}
		}
	}
	
	protected abstract void calculateNeighbors(Cell cell, int row, int col);
	
	protected abstract void updateGrid();
	
	protected void setNextStates() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				cell.setState(nextState[row][col]);
			}
		}
	}
}
