package cellsociety_team07;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * This class represents the Menu Scene from where a simulation can be started.
 */
public class Menu {
	private Scene menuScene;
	private Group root;
	
	private EventHandler<ActionEvent> goToSegregation;
	private EventHandler<ActionEvent> goToPredatorPrey;
	private EventHandler<ActionEvent> goToFire;
	private EventHandler<ActionEvent> goToGameOfLife;
	
	/**
	 * Constructor for Menu class
	 */
	public Menu(EventHandler<ActionEvent> segregation, EventHandler<ActionEvent> predatorPrey,
			EventHandler<ActionEvent> fire, EventHandler<ActionEvent> gameOfLife) {
		this.goToSegregation = segregation;
		this.goToPredatorPrey = predatorPrey;
		this.goToFire = fire;
		this.goToGameOfLife = gameOfLife;
	}
	
	/**
	 * Returns the Menu Scene
	 * @param width the width of the Scene
	 * @param height the height of the Scene
	 * @return Scene with the specified attributes
	 */
	public Scene init() {
		root = new Group();
		menuScene = new Scene(root, Main.SIZE, Main.SIZE, Color.AZURE);
		
		addSimulationStartButtons();
		
		return menuScene;
	}
	
	private void addSimulationStartButtons() {
		Button segregationBtn = UIGenerator.createButton("Start Segregation Simulation", 50, 50);
		segregationBtn.setOnAction(goToSegregation);
		
		Button predatorPreyBtn = UIGenerator.createButton("Start Predator-Prey Simulation", 50, 100);
		predatorPreyBtn.setOnAction(goToPredatorPrey);
		
		Button fireBtn = UIGenerator.createButton("Start Fire Simulation", 50, 150);
		fireBtn.setOnAction(goToFire);
		
		Button gameOfLifeBtn = UIGenerator.createButton("Start Game-of-Life Simulation", 50, 200);
		gameOfLifeBtn.setOnAction(goToGameOfLife);
		
		root.getChildren().add(segregationBtn);
		root.getChildren().add(predatorPreyBtn);
		root.getChildren().add(fireBtn);
		root.getChildren().add(gameOfLifeBtn);
	}
}
