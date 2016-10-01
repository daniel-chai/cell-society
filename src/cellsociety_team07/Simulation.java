package cellsociety_team07;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This class is the abstract superclass for all the different types of simulations. It is meant
 * to be extended. Each particular simulation has different rules, so each particular simulation
 * will extend this Simulation superclass and implement its own rules.
 */
public abstract class Simulation {
    public static final int MILLISECOND_DELAY = 500;
    
	protected Scene simulationScene;
	protected Group root;
	protected Timeline animation;
	
	protected EventHandler<ActionEvent> goToMenu;
	
	protected Grid grid;
	protected int rows;
	protected int columns;
	
	protected Group gridUI;
	protected Map<State, Color> colorMap;
	protected State[][] nextState;
	
	protected Simulation(EventHandler<ActionEvent> goToMenu, int rows, int columns) {
		this.goToMenu = goToMenu;
		this.rows = rows;
		this.columns = columns;
	}
	
	public Scene init() {
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		addPlayButton();
		addStopButton();
		
		initColors();
		initGrid();
		
		return simulationScene;
	}
	
	protected abstract void initColors();
	
	protected void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Back to Menu", 20, 20, 150, 20, 15);
		menuButton.setOnAction(goToMenu);
		root.getChildren().add(menuButton);
	}
	
	protected void addStepButton() {
		Button stepButton = UIGenerator.createButton("Show Next Step", 200, 20, 150, 20, 15);
		stepButton.setOnAction(e -> updateGrid());
		root.getChildren().add(stepButton);
	}
	
	protected void addPlayButton() {
		Button playButton = UIGenerator.createButton("Play", 380, 20, 100, 20, 15);
		playButton.setOnAction(e -> activatePlay());
		root.getChildren().add(playButton);
	}
	
	protected void activatePlay() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateGrid());
		
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	protected void addStopButton() {
		Button stopButton = UIGenerator.createButton("Stop", 500, 20, 100, 20, 15);
		stopButton.setOnAction(e -> activateStop());
		root.getChildren().add(stopButton);
	}
	
	protected void activateStop() {
		animation.stop();
	}
	
	protected void displayGrid() {		
		gridUI = new Group();
		root.getChildren().add(gridUI);
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				
				Cell cell = grid.getCell(row, col);
				Rectangle rect = cell.getRectangle();
				
				for (State s : colorMap.keySet()) {
					if (cell.getState().equals(s)) {
						rect.setFill(colorMap.get(s));
					}
				}
				
				rect.setX(100 + col * (Cell.CELL_SIZE + 1));
				rect.setY(100 + row * (Cell.CELL_SIZE + 1));
				
				gridUI.getChildren().add(rect);
			}
		} 
	}
	
	protected void initGrid() {
		grid = new Grid(rows, columns);		
		initStates();
		initNeighbors();
		
		displayGrid();
	}
	
	protected abstract void initStates();	
	
	protected void initNeighbors() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				calculateNeighbors(cell, row, col);
			}
		}
	}
	
	protected abstract void calculateNeighbors(Cell cell, int row, int col);
	
	protected abstract void updateGrid();
	
	protected void setNextStates() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				Cell cell = grid.getCell(row, col);
				cell.setState(nextState[row][col]);
			}
		}
	}
}
