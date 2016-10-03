package simulation;

import java.awt.Point;
import java.util.ArrayList;
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
import neighborhood_definer.NeighborhoodDefiner;
import neighborhood_definer.RectangleNeighborhoodDefiner;

/**
 * This class implements the SlimeMold simulation.
 * 
 * @author Daniel Chai
 */
public class SlimeMoldSimulation extends Simulation {
	private static final State stateEmpty = new State("EMPTY");
	private static final State stateMold = new State("MOLD");
	
	private double[][] cAMP;
	private double depositRate = 1.0;
	private double evaporationRate = 0.5;
	
	public SlimeMoldSimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns) {
		super(goToMenu, rows, columns);
		cAMP = new double[grid.getHeight()][grid.getWidth()];
	}

	@Override
	protected ColorMap initColors() {
		Map<State, Color> pairings = new HashMap<State, Color>();
		pairings.put(stateEmpty, Color.LIGHTGRAY);
		pairings.put(stateMold, Color.GREEN);
		return new ColorMap(pairings);
	}
	
	@Override
	protected void initStates() {
		for (Point point : grid.getPointsOnBoard()) {
			Cell cell = new Cell(generateRandomState());
			grid.addCell(cell, point);
		}
	}
	
	private State generateRandomState() {
		Random r = new Random();
		int i = r.nextInt(5);
		
		if (i == 0) {
			return stateMold;
		}
		else {
			return stateEmpty;
		}
	}
	
	@Override
	protected void updateGrid() {
		updatecAMP();
		calculateNextStates();
		
		setNextStates();
		displayGrid();
	}
	
	private void updatecAMP() {
		for (Point point : grid.getPointsOnBoard()) {
			int row = point.x;
			int col = point.y;
			
			Cell cell = grid.getCell(row, col);

			if (cell.getState().equals(stateMold)) {
				cAMP[row][col] += depositRate;
			}
			
			cAMP[row][col] *= evaporationRate;
		}
	}
	
	private void calculateNextStates() {
		nextState = new State[grid.getHeight()][grid.getWidth()];
		
		for (Point point : grid.getPointsOnBoard()) {
			int row = point.x;
			int col = point.y;
			
			Cell cell = grid.getCell(row, col);
			
			if (cell.getState().equals(stateEmpty) && nextState[row][col] == null) {
				nextState[row][col] = stateEmpty;
				continue;
			}
			
			if (cell.getState().equals(stateMold)) {
				moveMold(cell, row, col);
			}
		}
	}
	
	private void moveMold(Cell cell, int row, int col) {
		List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
		
		double maxcAMP = 0.0;
		Cell maxNeighbor = null;
		
		for (Cell neighbor : neighbors) {
			Point nPoint = grid.getLocation(neighbor);
			
			if (neighbor.getState().equals(stateEmpty)) {
				if (nextState[nPoint.x][nPoint.y] != null && nextState[nPoint.x][nPoint.y].equals(stateMold)) {
					continue;
				}
				if (cAMP[nPoint.x][nPoint.y] >= maxcAMP) {
					maxcAMP = cAMP[nPoint.x][nPoint.y];
					maxNeighbor = neighbor;
				}
			}
		}
		
		if (maxNeighbor == null) {
			nextState[row][col] = stateMold;
			return;
		}
		
		Point nPoint = grid.getLocation(maxNeighbor);
		nextState[nPoint.x][nPoint.y] = stateMold;
		nextState[row][col] = stateEmpty;
	}
	
	@Override
	protected NeighborhoodDefiner getNeighborhoodDefiner() {
		return new RectangleNeighborhoodDefiner(RectangleNeighborhoodDefiner.SURROUNDING_POINTS_COLLECTION);
	}
}
