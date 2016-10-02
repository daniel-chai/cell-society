package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import structure.GridView;
import structure.HexagonGridView;
import structure.Structure;
import structure.StructureView;

/**
 * This class implements the Game-of-Life simulation
 */
public class GameOfLifeSimulation extends Simulation {
	private static final State stateAlive = new State("ALIVE");
	private static final State stateDead = new State("DEAD");
	
	public GameOfLifeSimulation(SceneManager sceneManager, String rows, String columns) {
		super(sceneManager);
		this.rows = Integer.parseInt(rows);
		this.columns = Integer.parseInt(columns);
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
	
	public Collection<Point> getNeighborDisplacements()
	{
		ArrayList<Point> neighborDisplacements = new ArrayList<Point>();
		
		for(int i = -1; i <= 1; i++)
		{
			for(int j = -1; j <= 1; j++)
			{
				if(i == 0 && i == j)
				{
					continue;
				}
				
				neighborDisplacements.add(new Point(i,j));
			}
		}
		
		return neighborDisplacements;
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
