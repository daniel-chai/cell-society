package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Segregation simulation.
 */
public class SegregationSimulation extends Simulation {
	private static final String stateX = "X";
	private static final String stateO = "O";
	private static final String stateEmpty = "EMPTY";
	
	private double threshold; 		
	
	public SegregationSimulation(SceneManager sceneManager) {
		super(sceneManager);
	}

	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		
		threshold = 0.3; 	// hard-coded for now
		rows = 10;			// hard-coded for now
		columns = 10;		// hard-coded for now
		
		initGrid();
		
		return simulationScene;
	}
	
	@Override
	protected void initStates() {
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
			return new State(stateX);
		}
		if (i == 1) {
			return new State(stateO);
		}
		else {
			return new State(stateEmpty);
		}
	}
	
	@Override
	protected void calculateNeighbors(Cell cell, int row, int col) {
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
	
	@Override
	protected void updateGrid() {
		List<Point> emptyCells = getEmptyCells();
		List<Point> cellsToMove = getCellsToMove();
		moveCells(cellsToMove, emptyCells);
		updateNeighbors();
	}
	
	private List<Point> getEmptyCells() {
		List<Point> emptyCells = new ArrayList<Point>();
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				if (cell.getState().equals(new State(stateEmpty))) {
					emptyCells.add(new Point(row, col));
				}
			}
		}
		
		return emptyCells;
	}
	
	private List<Point> getCellsToMove() {
		List<Point> cellsToMove = new ArrayList<Point>();
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				if (!isCellSatisfied(cell)) {
					cellsToMove.add(new Point(row, col));
				}
			}
		}
		
		return cellsToMove;
	}
	
	private boolean isCellSatisfied(Cell cell) {
		if (cell.getState().equals(new State(stateEmpty))) {
			return true;
		}
		
		List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
		State cellState = cell.getState();
		
		int nonEmptyNeighbors = 0;
		int sameStateNeighbors = 0;
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(new State(stateEmpty))) {
				continue;
			}
				
			nonEmptyNeighbors++;
			if (neighbor.getState().equals(cellState)) {
				sameStateNeighbors++;
			}
		}
		
		if (nonEmptyNeighbors == 0) {
			return true;
		}
		return (double) sameStateNeighbors / nonEmptyNeighbors > threshold;
	}
	
	private void moveCells(List<Point> cellsToMove, List<Point> emptyCells) {
		for (Point cellPoint : cellsToMove) {
			Cell cell = grid.getCell(cellPoint.x, cellPoint.y);
			
			Point emptyCellPoint = emptyCells.remove(0);
			Cell emptyCell = grid.getCell(emptyCellPoint.x, emptyCellPoint.y);
			
			if (cell.getState().equals(new State(stateX))) {
				emptyCell.setState(new State(stateX));
			}
			else {
				emptyCell.setState(new State(stateO));
			}
			
			cell.setState(new State(stateEmpty));
		}
	}
}
