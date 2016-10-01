package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Predator-Prey simulation.
 */
public class PredatorPreySimulation extends Simulation {
	private static final String stateEmpty = "EMPTY";
	private static final String stateFish = "FISH";
	private static final String stateShark = "SHARK";
	
	private int fishTurnsToBreed;
	private int sharkTurnsToBreed;
	
	private int turnsSinceLastFishBreed = 0;
	private int turnsSinceLastSharkBreed = 0;
	
	public PredatorPreySimulation(SceneManager sceneManager, int rows, int columns, int fishTurnsToBreed, int sharkTurnsToBreed) {
		super(sceneManager);
		this.rows = rows;
		this.columns = columns;
		this.fishTurnsToBreed = fishTurnsToBreed;
		this.sharkTurnsToBreed = sharkTurnsToBreed;
	}
	
	@Override
	protected void initColors() {
		colorMap = new HashMap<State, Color>();
		colorMap.put(new State(stateEmpty), Color.BLUE);
		colorMap.put(new State(stateFish), Color.GREEN);
		colorMap.put(new State(stateShark), Color.YELLOW);
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
		int i = r.nextInt(10);
		
		if (i == 0) {
			return new State(stateShark);
		}
		if (i == 1 || i == 2 || i == 3) {
			return new State(stateFish);
		}
		else {
			return new State(stateEmpty);
		}
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
		moveSharks();
		setNextStates();
		
		moveFish();
		setNextStates();
		
		breedSharksAndFish();
		
		displayGrid();
	}
	
	private void moveSharks() {
		nextState = new State[rows][columns];
		
		State moveFrom = new State(stateShark);
		State[] moveTo = {new State(stateEmpty), new State(stateFish)};
		State[] otherStates = {new State(stateEmpty), new State(stateFish)};
		
		moveAnimals(moveFrom, moveTo, otherStates);
	}
	
	private void moveFish() {		
		nextState = new State[rows][columns];
		
		State moveFrom = new State(stateFish);
		State[] moveTo = {new State(stateEmpty)};
		State[] otherStates = {new State(stateEmpty), new State(stateShark)};
		
		moveAnimals(moveFrom, moveTo, otherStates);
	}
	
	private void moveAnimals(State moveFrom, State[] moveTo, State[] otherStates) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				
				if (cell.getState().equals(otherStates[0]) && nextState[row][col] == null) {
					nextState[row][col] = otherStates[0];
					continue;
				}
				
				if (cell.getState().equals(otherStates[1]) && nextState[row][col] == null) {
					nextState[row][col] = otherStates[1];
					continue;
				}
				
				if (cell.getState().equals(moveFrom)) {
					moveSingleAnimal(cell, row, col, moveFrom, moveTo);
				}
			}
		}
	}
	
	private void moveSingleAnimal(Cell cell, int row, int col, State moveFrom, State[] moveTo) {
		List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
		List<Cell> cellsToMoveTo = new ArrayList<Cell>();
		
		for (Cell neighbor : neighbors) {
			for (int i = 0; i < moveTo.length; i++) {
				if (neighbor.getState().equals(moveTo[i])) {
					cellsToMoveTo.add(neighbor);
					break;
				}
			}
		}
			
		if (cellsToMoveTo.isEmpty()) {
			nextState[row][col] = moveFrom;
			return;
		}
			
		Random r = new Random();
		Cell randomCell = cellsToMoveTo.get(r.nextInt(cellsToMoveTo.size()));
		
		Point neighborLocation = grid.getLocation(randomCell);		
		nextState[neighborLocation.x][neighborLocation.y] = moveFrom;
		nextState[row][col] = new State(stateEmpty);
	}
	
	private void breedSharksAndFish() {
		if (turnsSinceLastSharkBreed >= sharkTurnsToBreed) {
			breedAnimals(new State(stateShark), new State(stateFish), new State(stateEmpty));
			setNextStates();
			turnsSinceLastSharkBreed = 0;
		}
		turnsSinceLastSharkBreed++;

		if (turnsSinceLastFishBreed >= fishTurnsToBreed) {
			breedAnimals(new State(stateFish), new State(stateShark), new State(stateEmpty));
			setNextStates();
			turnsSinceLastFishBreed = 0;
		}
		turnsSinceLastFishBreed++;
	}
	
	private void breedAnimals(State stateToBreed, State otherState1, State otherState2) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				
				if (cell.getState().equals(otherState1) && nextState[row][col] == null) {
					nextState[row][col] = otherState1;
					continue;
				}
				
				if (cell.getState().equals(otherState2) && nextState[row][col] == null) {
					nextState[row][col] = otherState2;
					continue;
				}
				
				if (cell.getState().equals(stateToBreed)) {
					breedSingleAnimal(cell, row, col);
				}
			}
		}
	}
	
	private void breedSingleAnimal(Cell cell, int row, int col) {
		List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
		List<Cell> cellsToBreedTo = new ArrayList<Cell>();
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(new State(stateEmpty))) {
				cellsToBreedTo.add(neighbor);
			}
		}
			
		if (cellsToBreedTo.isEmpty()) {
			nextState[row][col] = cell.getState();
			return;
		}
			
		Random r = new Random();
		Cell randomCell = cellsToBreedTo.get(r.nextInt(cellsToBreedTo.size()));
		
		Point neighborLocation = grid.getLocation(randomCell);		
		nextState[neighborLocation.x][neighborLocation.y] = cell.getState();
		nextState[row][col] = cell.getState();				
	}
}
