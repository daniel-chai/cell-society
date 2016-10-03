package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import structure.GridView;
import structure.Structure;
import structure.StructureView;
import structure.HexagonGridView;

/**
 * This class implements the Fire simulation.
 * 
 * @author Daniel Chai
 */
public class FireSimulation extends Simulation {
	private static final State stateEmpty = new State("EMPTY");
	private static final State stateBurning = new State("BURNING");
	private static final State stateTree = new State("TREE");
	
	private double probCatch;
	private Point startCell;
	
	public FireSimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns, double probCatch, Point startCell) {
		super(goToMenu, rows, columns);
		this.probCatch = probCatch;
		this.startCell = startCell;
	}
	
	@Override
	protected ColorMap initColors() {
		Map<State,Color> pairings = new HashMap<State, Color>();
		pairings.put(stateEmpty, Color.YELLOW);
		pairings.put(stateBurning, Color.RED);
		pairings.put(stateTree, Color.GREEN);
		return new ColorMap(pairings);
	}
	
	@Override
	protected void initStates() {
		for(Point p : grid.getPointsOnBoard())
		{
			int row = p.x;
			int col = p.y;
			
			Cell cell = new Cell(stateTree);
			grid.addCell(cell, row, col);	
		}
		
		Cell cell = new Cell(stateBurning);
		grid.addCell(cell, startCell.x, startCell.y);		
	}
	
	@Override
	protected void updateGrid() {
		calculateNextStates();
		
		setNextStates();
		displayGrid();
	}
	
	private void calculateNextStates() {
		nextState = new State[grid.getHeight()][grid.getWidth()];
		
		for (Point point : grid.getPointsOnBoard())
		{
				int row = point.x;
				int col = point.y;
				
				Cell cell = grid.getCell(row, col);
				
				if (cell.getState().equals(stateEmpty)) {
					nextState[row][col] = stateEmpty;
				}
				
				if (cell.getState().equals(stateBurning)) {
					nextState[row][col] = stateEmpty;
				}
				
				if (cell.getState().equals(stateTree)) {
					List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
					if (doesTreeBecomeBurning(neighbors)) {
						nextState[row][col] = stateBurning;
					}
					else {
						nextState[row][col] = stateTree;
					}
				}
				
		}		
	}
	
	private boolean doesTreeBecomeBurning(List<Cell> neighbors) {
		boolean burningNeighbor = false;
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(stateBurning)) {
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
	
	@Override
	protected StructureView initStructureView() 
	{
		return new StructureView(grid,colorMap,300,300);
	}
	
	@Override
	protected Structure initStructure()
	{
		return new Grid(rows, columns);
	}
	
	@Override
	public Collection<Point> getNeighborDisplacements()
	{
		return Neighborhood.RECTANGLE_CROSS_POINTS_COLLECTION;
	}
}
