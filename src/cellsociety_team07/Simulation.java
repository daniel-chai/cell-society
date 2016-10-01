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

import ui_components.ButtonBuilder;

/**
 * This class is the abstract superclass for all the different types of simulations. It is meant
 * to be extended. Each particular simulation has different rules, so each particular simulation
 * will extend this Simulation superclass and implement its own rules.
 */
public abstract class Simulation {
    private static final int MILLISECOND_DELAY = 500;
    
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
		Button menuButton = new ButtonBuilder().setText("Back To Menu")
								.setXLocation(20)
								.setYLocation(20)
								.setWidth(150)
								.setHeight(20)
								.setFontSize(15)
								.build();
		
		menuButton.setOnAction(goToMenu);
		root.getChildren().add(menuButton);
	}
	
	protected void addStepButton() {
		Button stepButton = new ButtonBuilder().setText("Show Next Step")
								.setXLocation(220)
								.setYLocation(20)
								.setWidth(150)
								.setHeight(20)
								.setFontSize(15)
								.build();
		
		stepButton.setOnAction(e -> updateGrid());
		root.getChildren().add(stepButton);
	}
	
	protected void addPlayButton() {
		Button playButton = new ButtonBuilder().setText("Play")
								.setXLocation(400)
								.setYLocation(20)
								.setWidth(100)
								.setHeight(20)
								.setFontSize(15)
								.build();
		
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
		Button stopButton = new ButtonBuilder().setText("Stop")
								.setXLocation(510)
								.setYLocation(20)
								.setWidth(100)
								.setHeight(20)
								.setFontSize(15)
								.build();
		
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
