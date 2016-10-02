package cellsociety_team07;

import structure.Structure;
import structure.StructureView;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.Collection;
import java.awt.Point;

/**
 * This class is the abstract superclass for all the different types of simulations. It is meant
 * to be extended. Each particular simulation has different rules, so each particular simulation
 * will extend this Simulation superclass and implement its own rules.
 */
public abstract class Simulation {
    public static final int MILLISECOND_DELAY = 500;
	
	protected SceneManager sceneManager;
	protected Scene simulationScene;
	protected Group root;
	protected Timeline animation;
	
	protected Structure grid;
	protected int rows;
	protected int columns;
	
	protected Node gridUI;
	protected ColorMap colorMap;
	protected State[][] nextState;
	
	private StructureView gridView;
	
	protected Simulation(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
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
	
	protected void activateStop() 
	{
		animation.stop();
	}
	
	protected void displayGrid() 
	{		
		gridView.updateView();
	}
	
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
	
	protected StructureView initStructureView() 
	{
		return new StructureView(grid,colorMap,300,300);
	}
	
	protected abstract Structure initStructure();
	
	protected abstract void initStates();	
	
	protected void initNeighbors() 
	{
		Collection<Point> neighborLocations = getNeighborDisplacements();
		grid.calculateNeighborsForCells(neighborLocations);
	}
	
	protected abstract void updateGrid();
	
	protected abstract Collection<Point> getNeighborDisplacements();
	
	protected void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Back to Menu", 20, 20, 150, 20, 15);
		menuButton.setOnAction(e -> sceneManager.goToMenuScene(sceneManager));
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
	
	protected void setNextStates() {
		for(Point p : grid.getPointsOnBoard()) {
				int row = p.x;
				int col = p.y;
				Cell cell = grid.getCell(row, col);
				cell.setState(nextState[row][col]);
		}
	}
}
