package cellsociety_team07;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

/**
 * This class implements the Fire simulation.
 */
public class FireSimulation extends Simulation {
	private static final String stateEmpty = "EMPTY";
	private static final String stateBurning = "BURNING";
	private static final String stateTree = "TREE";
	
	private double probCatch;
	private Point startCell;
	
	public FireSimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns, double probCatch, Point startCell) {
		super(goToMenu, rows, columns);
		this.probCatch = probCatch;
		this.startCell = startCell;
	}
	
	@Override
	protected void initColors() {
		colorMap = new HashMap<State, Color>();
		colorMap.put(new State(stateEmpty), Color.YELLOW);
		colorMap.put(new State(stateBurning), Color.RED);
		colorMap.put(new State(stateTree), Color.GREEN);
	}
	
	@Override
	protected void initStates() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = new Cell(new State(stateTree));
				grid.addCell(cell, row, col);
			}
		}
		
		Cell cell = new Cell(new State(stateBurning));
		grid.addCell(cell, startCell.x, startCell.y);		
	}

	@Override
	protected void calculateNeighbors(Cell cell, int row, int col) {
		Point[] neighborDisplacements = {new Point(1,0), new Point(-1,0), new Point(0,1), new Point(0,-1)};
		
		for (Point neighborDisplacement : neighborDisplacements) {
			int i = neighborDisplacement.x;
			int j = neighborDisplacement.y;
			
			if (i + row < 0 || i + row >= rows || j + col < 0 || j + col >= columns) {
				continue;
			}
			Cell neighbor = grid.getCell(row + i, col + j);
			cell.getNeighborhood().addNeighbor(neighbor);
		}
	}
	
	@Override
	protected void updateGrid() {
		calculateNextStates();
		
		setNextStates();
		displayGrid();
	}
	
	private void calculateNextStates() {
		nextState = new State[rows][columns];
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				
				if (cell.getState().equals(new State(stateEmpty))) {
					nextState[row][col] = new State(stateEmpty);
				}
				
				if (cell.getState().equals(new State(stateBurning))) {
					nextState[row][col] = new State(stateEmpty);
				}
				
				if (cell.getState().equals(new State(stateTree))) {
					List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
					if (doesTreeBecomeBurning(neighbors)) {
						nextState[row][col] = new State(stateBurning);
					}
					else {
						nextState[row][col] = new State(stateTree);
					}
				}
				
			}
		}		
	}
	
	private boolean doesTreeBecomeBurning(List<Cell> neighbors) {
		boolean burningNeighbor = false;
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(new State(stateBurning))) {
				burningNeighbor = true;
				break;
			}
		}
		
		if (burningNeighbor) {
			Random r = new Random();
			return r.nextDouble() < probCatch;
		}
		else {
			return false;
		}
	}
}
