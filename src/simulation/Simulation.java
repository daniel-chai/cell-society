package simulation;

import structure.CircleStructure;
import structure.HexagonStructure;
import structure.RectangleStructure;
import structure.Structure;
import structure.TriangleStructure;
import structure_view.StructureView;

import java.util.Map;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import cellsociety_team07.Main;
import cellsociety_team07.State;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import neighborhood.Neighborhood;
import neighborhood_definer.NeighborhoodDefiner;

import java.util.Collection;
import java.awt.Point;

import ui_components.ButtonBuilder;

/**
 * This class is the abstract superclass for all the different types of simulations. It is meant
 * to be extended. Each particular simulation has different rules, so each particular simulation
 * will extend this Simulation superclass and implement its own rules.
 * 
 * @author Daniel Chai
 */
public abstract class Simulation {
    private static final int MILLISECOND_DELAY = 500;
    
	protected Scene simulationScene;
	protected Group root;
	protected Timeline animation;
	
	protected Structure grid;
	protected EventHandler<ActionEvent> goToMenu;
	
	protected int rows;
	protected int columns;
	
	protected Node gridUI;
	protected ColorMap colorMap;
	protected State[][] nextState;
	
	private StructureView gridView;
	
	protected Simulation(EventHandler<ActionEvent> goToMenu, int rows, int columns) {
		this.goToMenu = goToMenu;
		this.rows = rows;
		this.columns = columns;
	}
	
	public Scene init() 
	{
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		addMenuButton();
		addStepButton();
		addPlayButton();
		addStopButton();
		
		colorMap = initColors();
		initGrid();
		
		return simulationScene;
	}
	
	protected abstract ColorMap initColors();
	
	protected void initGrid() {
		grid = initStructure();		
		initStates();
		initNeighbors();
		
		gridView = initStructureView();

		Node gridUI = gridView.getNode();
		gridUI.setLayoutX(100);
		gridUI.setLayoutY(100);
		 
		root.getChildren().add(gridUI);
		
		displayGrid();
	}
	
	protected Structure initStructure()
	{
		return new RectangleStructure(rows, columns);
	}
	
	protected abstract void initStates();	
	
	protected void initNeighbors() 
	{
		NeighborhoodDefiner neighborhoodDefiner = getNeighborhoodDefiner();
		grid.calculateNeighborsForCells(neighborhoodDefiner);
	}
	
	protected StructureView initStructureView() 
	{
		return new StructureView(grid,colorMap,500,500);
	}
	
	protected void displayGrid() 
	{		
		gridView.updateView();
	}
	
	protected abstract void updateGrid();
	
	protected abstract NeighborhoodDefiner getNeighborhoodDefiner();
	
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
	
	protected void activateStop() 
	{
		animation.stop();
	}
	
	protected void setNextStates() {
		for(Point p : grid.getPointsOnBoard()) {
				int row = p.x;
				int col = p.y;
				Cell cell = grid.getCell(row, col);
				cell.setState(nextState[row][col]);
		}
	}
}
