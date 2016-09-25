package cellsociety_team07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * This class implements the Game-of-Life simulation
 */
public class GameOfLifeSimulation extends Simulation {
	private static final String stateAlive = "ALIVE";
	private static final String stateDead = "DEAD";
	
	public GameOfLifeSimulation(SceneManager sceneManager, String rows, String columns) {
		super(sceneManager);
		this.rows = Integer.parseInt(rows);
		this.columns = Integer.parseInt(columns);
	}

	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		
		initColors();
		initGrid();
		
		return simulationScene;
	}

	private void initColors() {
		colorMap = new HashMap<State, Color>();
		colorMap.put(new State(stateAlive), Color.BLACK);
		colorMap.put(new State(stateDead), Color.LIGHTGREY);	
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
		int i = r.nextInt(4);
		
		if (i == 0) {
			return new State(stateAlive);
		}
		else {
			return new State(stateDead);
		}
	}
	
	@Override
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
		calculateNextStates();
		
		setNextStates();
		displayGrid();
	}
	
	private void calculateNextStates() {
		nextState = new State[rows][columns];
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				List<Cell> neighbors = cell.getNeighborhood().getNeighbors();
				
				int aliveNeighbors = countAliveNeighbors(neighbors);
				
				if (cell.getState().equals(new State(stateAlive))) {
					if (aliveNeighbors < 2 || aliveNeighbors > 3) {
						nextState[row][col] = new State(stateDead);
					}
					else {
						nextState[row][col] =  new State(stateAlive);
					}
				}
				
				if (cell.getState().equals(new State(stateDead))) {
					if (aliveNeighbors == 3) {
						nextState[row][col] =  new State(stateAlive);
					}
					else {
						nextState[row][col] = new State(stateDead);
					}
				}
			}
		}
	}
	
	private int countAliveNeighbors(List<Cell> neighbors) {
		int aliveNeighbors = 0;
		
		for (Cell neighbor : neighbors) {
			if (neighbor.getState().equals(new State(stateAlive))) {
				aliveNeighbors++;
			}
		}
		
		return aliveNeighbors;
	}
}
