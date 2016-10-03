package cellsociety_team07;

import java.awt.Point;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simulation.FireSimulation;
import simulation.GameOfLifeSimulation;
import simulation.PredatorPreySimulation;
import simulation.SegregationSimulation;
import simulation.SlimeMoldSimulation;
import xml.FireData;
import xml.LifeData;
import xml.PredData;
import xml.SegregationData;
/**
 * This class handles all the scene changes. In the whole application, there is only one Stage that is 
 * used. That is why this class takes in the primaryStage as a parameter in the constructor. All the 
 * scene changes are done on this Stage. 
 * 
 * @author Daniel Chai
 */
public class SceneManager {
	private static final String SEGREGATION = "Schelling's Model Of Segregation";
	private static final String PREDATOR_PREY = "Predator-Prey";
	private static final String FIRE = "Spreading Of Fire";
	private static final String GAME_OF_LIFE = "Conway's Game of Life";
	
	private Stage stage;
	
	private EventHandler<ActionEvent> goToMenu; 
	private EventHandler<ActionEvent> goToSegregation;
	private EventHandler<ActionEvent> goToPredatorPrey;
	private EventHandler<ActionEvent> goToFire;
	private EventHandler<ActionEvent> goToGameOfLife;
	private EventHandler<ActionEvent> goToSlimeMold;
	
	/**
	 * Constructor for SceneManager class
	 * @param primaryStage the Stage that this SceneManager uses
	 */
	public SceneManager(Stage primaryStage) {
		this.stage = primaryStage;
		
		initEventHandlers();
		
		goToMenuScene();
		stage.show();
	}
	
	private void initEventHandlers() {
		goToMenu = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	goToMenuScene();
            }
		};
		
		goToSegregation = new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				goToSegregationScene();
			}
		};
		
		goToPredatorPrey = new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				goToPredatorPreyScene();
			}
		};
		
		goToFire = new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				goToFireScene();
			}
		};
		
		goToGameOfLife = new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				goToGameOfLifeScene();
			}
		};
		
		goToSlimeMold = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				goToSlimeMoldScene();
			}
		};
	}
	
	private void goToMenuScene() {
		Menu menu = new Menu(goToSegregation, goToPredatorPrey, goToFire, goToGameOfLife, goToSlimeMold);
		Scene menuScene = menu.init();
		stage.setScene(menuScene);
	}
	
	private void goToSegregationScene() {
		SegregationData data = (SegregationData) DataInput.getInputData(SEGREGATION);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		double threshold = data.getMyThreshold();
		
		SegregationSimulation simulation = new SegregationSimulation(goToMenu, rows, cols, threshold);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	private void goToPredatorPreyScene() {
		PredData data = (PredData) DataInput.getInputData(PREDATOR_PREY);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		int fishTurnsToBreed = data.getMyFishBreed();
		int sharkTurnsToBreed = data.getMySharkBreed();
		
		PredatorPreySimulation simulation = new PredatorPreySimulation(goToMenu, rows, cols, fishTurnsToBreed, sharkTurnsToBreed);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}

	private void goToFireScene() {
		FireData data = (FireData) DataInput.getInputData(FIRE);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		double probCatch = data.getMyProbCatch();
		Point startCell = data.getMyInitialFire();
		
		FireSimulation simulation = new FireSimulation(goToMenu, rows, cols, probCatch, startCell);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	private void goToGameOfLifeScene() {
		LifeData data = (LifeData) DataInput.getInputData(GAME_OF_LIFE);
		
		int rows = data.getMyNumRows();
		int cols = data.getMyNumCols();
		
		GameOfLifeSimulation simulation = new GameOfLifeSimulation(goToMenu, rows, cols);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
	
	private void goToSlimeMoldScene() {
		SlimeMoldSimulation simulation = new SlimeMoldSimulation(goToMenu, 20, 20);
		Scene simulationScene = simulation.init();
		stage.setScene(simulationScene);
	}
}
