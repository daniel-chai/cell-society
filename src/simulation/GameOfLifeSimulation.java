package simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import cellsociety_team07.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import neighborhood.Neighborhood;
import neighborhood_definer.NeighborhoodDefiner;
import neighborhood_definer.RectangleNeighborhoodDefiner;
import structure.RectangleStructure;
import structure.Structure;
import structure_view.GridView;
import structure_view.HexagonGridView;
import structure_view.StructureView;

/**
 * This class implements the Game-of-Life simulation
 * 
 * @author Daniel Chai
 */
public class GameOfLifeSimulation extends Simulation {
	private static final State stateAlive = new State("ALIVE");
	private static final State stateDead = new State("DEAD");
	
	public GameOfLifeSimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns) {
		super(goToMenu, rows, columns);
	}

	@Override
	protected ColorMap initColors() {
		Map<State,Color> pairings = new HashMap<State, Color>();
		pairings.put(stateAlive, Color.BLACK);
		pairings.put(stateDead, Color.LIGHTGREY);	
		return new ColorMap(pairings);
	}
	
	@Override
	protected void initStates() {
		for (Point p : grid.getPointsOnBoard())
		{
				int row = p.x;
				int col = p.y;
				
				Cell cell = new Cell(generateRandomState());
				grid.addCell(cell, row, col);
		}
	}
	
	private State generateRandomState() {
		Random r = new Random();
		int i = r.nextInt(4);
		
		if (i == 0) {
			return stateAlive;
		}
		else {
			return stateDead;
		}
	}
	
	@Override
	protected void updateGrid() {
		calculateNextStates();
		
		setNextStates();
		displayGrid();
	}
	
	private void calculateNextStates() {
		nextState = new State[grid.getHeight()][grid.getWidth()];
		
		for (Point p : grid.getPointsOnBoard())
		{
				int row = p.x;
				int col = p.y;
				
				Cell cell = grid.getCell(row, col);
				List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
				
				int aliveNeighbors = countAliveNeighbors(neighbors);
				
				if (cell.getState().equals(stateAlive)) {
					if (aliveNeighbors < 2 || aliveNeighbors > 3) {
						nextState[row][col] = stateDead;
					}
					else {
						nextState[row][col] =  stateAlive;
					}
				}
				
				if (cell.getState().equals(stateDead)) {
					if (aliveNeighbors == 3) {
						nextState[row][col] =  stateAlive;
					}
					else {
						nextState[row][col] = stateDead;
					}
				}
		}
	}
	
	private int countAliveNeighbors(List<Cell> neighbors) {
		int aliveNeighbors = 0;
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(stateAlive)) {
				aliveNeighbors++;
			}
		}
		
		return aliveNeighbors;
	}
	
	@Override
	public NeighborhoodDefiner getNeighborhoodDefiner()
	{
		return new RectangleNeighborhoodDefiner(RectangleNeighborhoodDefiner.SURROUNDING_POINTS_COLLECTION);
	}
}
