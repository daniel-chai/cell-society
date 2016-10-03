package simulation;

import java.awt.Point;

import structure.RectangleStructure;
import structure.Structure;
import view.HexagonStructureView;
import view.RectangleStructureView;
import view.StructureView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import cellsociety_team07.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import neighborhood.Neighborhood;

import neighborhood_definer.NeighborhoodDefiner;
import neighborhood_definer.RectangleNeighborhoodDefiner;

import java.util.Collection;
import java.util.Collections;

/**
 * This class implements the Predator-Prey simulation.
 * 
 * @author Daniel Chai
 */
public class PredatorPreySimulation extends Simulation {
	private static final State stateEmpty = new State("EMPTY");
	private static final State stateFish = new State("FISH");
	private static final State stateShark = new State("SHARK");
	
	private int fishTurnsToBreed;
	private int sharkTurnsToBreed;
	
	private int turnsSinceLastFishBreed = 0;
	private int turnsSinceLastSharkBreed = 0;
	
	public PredatorPreySimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns, int fishTurnsToBreed, int sharkTurnsToBreed) {
		super(goToMenu, rows, columns);
		this.fishTurnsToBreed = fishTurnsToBreed;
		this.sharkTurnsToBreed = sharkTurnsToBreed;
	}
	
	@Override
	protected ColorMap initColors() {
		HashMap<State,Color> pairings = new HashMap<State, Color>();
		pairings.put(stateEmpty, Color.BLUE);
		pairings.put(stateFish, Color.GREEN);
		pairings.put(stateShark, Color.YELLOW);
		return new ColorMap(pairings);
	}
	
	@Override
	protected void initStates() {
		for(Point p : grid.getPointsOnBoard())
		{
			Cell cell = new Cell(generateRandomState());
			grid.addCell(cell,p);
		}
	}
	
	private State generateRandomState() {
		Random r = new Random();
		int i = r.nextInt(10);
		
		if (i == 0) {
			return stateShark;
		}
		if (i == 1 || i == 2 || i == 3) {
			return stateFish;
		}
		else {
			return stateEmpty;
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
		nextState = new State[grid.getHeight()][grid.getWidth()];
		
		State moveFrom = stateShark;
		State[] moveTo = {stateEmpty, stateFish};
		State[] otherStates = {stateEmpty, stateFish};
		
		moveAnimals(moveFrom, moveTo, otherStates);
	}
	
	private void moveFish() {		
		nextState = new State[grid.getHeight()][grid.getWidth()];
		
		State moveFrom = stateFish;
		State[] moveTo = {stateEmpty};
		State[] otherStates = {stateEmpty, stateShark};
		
		moveAnimals(moveFrom, moveTo, otherStates);
	}
	
	private void moveAnimals(State moveFrom, State[] moveTo, State[] otherStates) {
		for (Point point : grid.getPointsOnBoard())
		{
				Cell cell = grid.getCell(point);
				
				int row = point.x;
				int col = point.y;
				
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
		nextState[row][col] = stateEmpty;
	}
	
	private void breedSharksAndFish() {
		if (turnsSinceLastSharkBreed >= sharkTurnsToBreed) {
			breedAnimals(stateShark, stateFish, stateEmpty);
			setNextStates();
			turnsSinceLastSharkBreed = 0;
		}
		turnsSinceLastSharkBreed++;

		if (turnsSinceLastFishBreed >= fishTurnsToBreed) {
			breedAnimals(stateFish, stateShark, stateEmpty);
			setNextStates();
			turnsSinceLastFishBreed = 0;
		}
		turnsSinceLastFishBreed++;
	}
	
	private void breedAnimals(State stateToBreed, State otherState1, State otherState2) {
		for (Point point : grid.getPointsOnBoard())
		{
				Cell cell = grid.getCell(point);
				
				int row = point.x;
				int col = point.y;
				
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
	
	private void breedSingleAnimal(Cell cell, int row, int col) {
		List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
		List<Cell> cellsToBreedTo = new ArrayList<Cell>();
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(stateEmpty)) {
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
	
	@Override
	public NeighborhoodDefiner getNeighborhoodDefiner()
	{
		return new RectangleNeighborhoodDefiner(RectangleNeighborhoodDefiner.CROSS_POINTS_COLLECTION);
	}	
}
