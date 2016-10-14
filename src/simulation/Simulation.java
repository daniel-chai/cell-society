package simulation;

import structure.CircleStructure;
import structure.HexagonStructure;
import structure.RectangleStructure;
import structure.Structure;
import structure.TriangleStructure;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import neighborhood.Neighborhood;
import neighborhood_definer.HexagonNeighborhoodDefiner;
import neighborhood_definer.NeighborhoodDefiner;
import neighborhood_definer.TriangleNeighborhoodDefiner;

import java.util.Collection;
import java.awt.Point;

import ui_components.ButtonBuilder;
import view.CellView;
import view.HexagonStructureView;
import view.RectangleStructureView;
import view.StructureView;
import view.TriangleStructureView;

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
	protected String cellViewType;
	protected ColorMap colorMap;
	protected State[][] nextState;
	
	private StructureView gridView;
	
	protected Simulation(EventHandler<ActionEvent> goToMenu, int rows, int columns) {
		this.goToMenu = goToMenu;
		this.rows = rows;
		this.columns = columns;
		this.cellViewType = CellView.RECTANGLE;
	}
	
	public Scene init() 
	{
		root = new Group();
		simulationScene = new Scene(root, Main.SIZE, Main.SIZE, Color.WHITE);
		
		initButtonsAndGrid();
		
		return simulationScene;
	}
	
	protected void initButtonsAndGrid() {
		addMenuButton();
		addStepButton();
		addPlayButton();
		addStopButton();
		addParamChanger();
		addRandomizeButton();
		addSquareSelector();
		addTriangleSelector();
		addHexagonSelector();
		
		colorMap = initColors();
		initGrid();
	}
	
	protected abstract ColorMap initColors();
	
	protected void initGrid() {
		grid = initStructure();		
		initStates();
		initNeighbors();
		
		gridView = initStructureView();

		configureGridUI();
		
		displayGrid();
	}
	
	protected Structure initStructure()
	{
		return new HexagonStructure(rows);
	}
	
	protected abstract void initStates();	
	
	protected void initNeighbors() 
	{
		grid.setNeighborhoods(getNeighborhoodDefiner());
		
	}
	
	protected StructureView initStructureView() 
	{
		return new HexagonStructureView((HexagonStructure)grid,colorMap,300,300,cellViewType);
	}
	
	protected void displayGrid() 
	{		
		gridView.updateView();
	}
	
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
	
	protected void addParamChanger() {
		Label rowLabel = new Label("Rows:");
		TextField rowField = new TextField();
		rowField.setMaxWidth(50);
		
		HBox rowParam = new HBox();
		rowParam.getChildren().addAll(rowLabel, rowField);
		rowParam.setSpacing(10);
		rowParam.setLayoutX(20);
		rowParam.setLayoutY(625);
		
		Label colLabel = new Label("Columns:");
		TextField colField = new TextField();
		colField.setMaxWidth(50);
		
		HBox colParam = new HBox();
		colParam.getChildren().addAll(colLabel, colField);
		colParam.setSpacing(10);
		colParam.setLayoutX(140);
		colParam.setLayoutY(625);
		
		Button paramButton = new ButtonBuilder().setText("Update parameters")
								.setXLocation(300)
								.setYLocation(625)
								.setWidth(150)
								.setHeight(20)
								.setFontSize(15)
								.build();
		
		paramButton.setOnAction(e -> activateParams(rowField, colField));
		
		root.getChildren().add(rowParam);
		root.getChildren().add(colParam);
		root.getChildren().add(paramButton);
	}
	
	protected void activateParams(TextField rowField, TextField colField) {
	    try{
		rows = Integer.parseInt(rowField.getText());
		columns = Integer.parseInt(colField.getText());
	    }
	    catch(NumberFormatException e){
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Message");
	        alert.setHeaderText("Error!");
	        alert.setContentText("Please Enter Valid Values");
	        alert.showAndWait();
	    }
	    
		root.getChildren().clear();
		cellViewType = gridView.getCellViewType();
		initButtonsAndGrid();
	}
	
	protected void addRandomizeButton() {
		Button randomButton = new ButtonBuilder().setText("Randomize States")
				.setXLocation(500)
				.setYLocation(625)
				.setWidth(100)
				.setHeight(20)
				.setFontSize(15)
				.build();

		randomButton.setOnAction(e -> activateRandom());
		root.getChildren().add(randomButton);
	}
	
	protected void activateRandom() {
		root.getChildren().clear();
		initButtonsAndGrid();
	}
	
	protected void addSquareSelector() {
		Button b = new ButtonBuilder().setText("Set Cells to Squares")
				.setXLocation(20)
				.setYLocation(560)
				.setWidth(100)
				.setHeight(20)
				.setFontSize(15)
				.build();
		
		b.setOnAction(e -> activateSquares());
		root.getChildren().add(b);
	}
	
	protected void addTriangleSelector() {
		Button b = new ButtonBuilder().setText("Set Cells to Triangles")
				.setXLocation(220)
				.setYLocation(560)
				.setWidth(100)
				.setHeight(20)
				.setFontSize(15)
				.build();
		
		b.setOnAction(e -> activateTriangles());
		root.getChildren().add(b);
	}
	
	protected void addHexagonSelector() {
		Button b = new ButtonBuilder().setText("Set Cells to Hexagons")
				.setXLocation(420)
				.setYLocation(560)
				.setWidth(100)
				.setHeight(20)
				.setFontSize(15)
				.build();
		
		b.setOnAction(e -> activateHexagons());
		root.getChildren().add(b);
	}


	protected void activateSquares() 
	{
		gridView.setCellView(CellView.RECTANGLE);
		grid.setNeighborhoods(getNeighborhoodDefiner());
		resetGridUI();
	}
	
	protected void activateTriangles() 
	{
		gridView.setCellView(CellView.TRIANGLE);
		grid.setNeighborhoods(new TriangleNeighborhoodDefiner());
		resetGridUI();
	}
	
	protected void activateHexagons() 
	{
		gridView.setCellView(CellView.HEXAGON);
		resetGridUI();
		grid.setNeighborhoods(new HexagonNeighborhoodDefiner());
		
	}
	
	private void resetGridUI()
	{
		root.getChildren().remove(gridUI);
		configureGridUI();
	}
	
	private void configureGridUI()
	{
		gridUI = gridView.getNode();
		gridUI.setLayoutX(Main.SIZE/2 - gridView.getGridWidth()/2); // MAGIC NUMBERS
		gridUI.setLayoutY(100); // MAGIC NUMBERS
		 
		root.getChildren().add(gridUI);
	}
	
	protected abstract void updateGrid();
	protected abstract NeighborhoodDefiner getNeighborhoodDefiner();

}
