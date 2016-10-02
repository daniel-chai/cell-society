package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import structure.GridView;
import structure.HexagonGridView;
import structure.Structure;
import structure.StructureView;

/**
 * This class implements the Segregation simulation.
 */
public class SegregationSimulation extends Simulation {
	private static final State stateX = new State("X");
	private static final State stateO = new State("O");
	private static final State stateEmpty = new State("EMPTY");
	
	private double threshold;
	private StructureView boardView;
	
	public SegregationSimulation(SceneManager sceneManager, String rows, String columns, String threshold) {
		super(sceneManager);
	}

	public Scene init() {
//		root = new Group();
//		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
//		
//		addMenuButton();
//		addStepButton();
//		
//		rows = 10;			// hard-coded for now
//		columns = 10;		// hard-coded for now
//		initGrid();
//		threshold = 0.3;
//		
		threshold = 0.3;
		rows = 10;
		columns = 10;
		
		return super.init();
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
				int row = point.x;
				int col = point.y;
				
				Cell cell = new Cell(generateRandomState());
				grid.addCell(cell, row, col);
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
	public Collection<Point> getNeighborDisplacements()
	{
		return Neighborhood.SURROUNDING_POINTS_COLLECTION;
	}
	
	protected void calculateNeighbors(Cell cell, int row, int col) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
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
	protected StructureView initStructureView() 
	{
		return new StructureView(grid,colorMap,300,300);
	}

	@Override
	protected Structure initStructure()
	{
		return new HexagonGrid(10);
	}
}
