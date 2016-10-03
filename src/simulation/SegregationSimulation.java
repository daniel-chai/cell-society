package simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import cellsociety_team07.State;

import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
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
 * This class implements the Segregation simulation.
 * 
 * @author Daniel Chai
 */
public class SegregationSimulation extends Simulation {
	private static final State stateX = new State("X");
	private static final State stateO = new State("O");
	private static final State stateEmpty = new State("EMPTY");
	
	private double threshold;

	public SegregationSimulation(EventHandler<ActionEvent> goToMenu, int rows, int columns, double threshold) {
		super(goToMenu, rows, columns);
		this.threshold = threshold;
	}
	
	@Override
	protected ColorMap initColors() {
		HashMap<State,Color> pairings = new HashMap<State, Color>();
		pairings.put(stateX, Color.BLUE);
		pairings.put(stateO, Color.RED);
		pairings.put(stateEmpty, Color.LIGHTGREY);
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
		int i = r.nextInt(4);
		
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
	
	@Override
	protected void updateGrid() {
		List<Point> emptyCells = getEmptyCells();
		List<Point> cellsToMove = getCellsToMove();
		moveCells(cellsToMove, emptyCells);
		 
		displayGrid();
	}
	
	private List<Point> getEmptyCells() {
		List<Point> emptyCells = new ArrayList<Point>();
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				if (cell.getState().equals(stateEmpty)) {
					emptyCells.add(new Point(row, col));
				}
			}
		}
		
		return emptyCells;
	}
	
	private List<Point> getCellsToMove() {
		List<Point> cellsToMove = new ArrayList<Point>();
		
		for (Point point : grid.getPointsOnBoard())
		{
			Cell cell = grid.getCell(point);
				if (!isCellSatisfied(cell)) {
					cellsToMove.add(point);
				}
		}
		
		return cellsToMove;
	}
	
	private boolean isCellSatisfied(Cell cell) {
		if (cell.getState().equals(stateEmpty)) {
			return true;
		}
		
		List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
		State cellState = cell.getState();
		
		int nonEmptyNeighbors = 0;
		int sameStateNeighbors = 0;
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(stateEmpty)) {
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
		return (double)(sameStateNeighbors) / nonEmptyNeighbors >= threshold;
	}
	
	private void moveCells(List<Point> cellsToMove, List<Point> emptyCells) {
		for (Point cellPoint : cellsToMove) {
			if (emptyCells.isEmpty()) {
				return;
			}
			
			Cell cell = grid.getCell(cellPoint.x, cellPoint.y);
			
			Random r = new Random();
			int randomEmpty = r.nextInt(emptyCells.size());
			Point emptyCellPoint = emptyCells.remove(randomEmpty);
			Cell emptyCell = grid.getCell(emptyCellPoint.x, emptyCellPoint.y);
			
			if (cell.getState().equals(stateX)) {
				emptyCell.setState(stateX);
			}
			else {
				emptyCell.setState(stateO);
			}
			
			cell.setState(stateEmpty);
		}
	}
	
	@Override
	public NeighborhoodDefiner getNeighborhoodDefiner()
	{
		return new RectangleNeighborhoodDefiner(RectangleNeighborhoodDefiner.SURROUNDING_POINTS_COLLECTION);
	}
}
