package cellsociety_team07;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import ui_components.ButtonBuilder;

/**
 * This class represents the Menu Scene from where a simulation can be started.
 * 
 * @author Daniel Chai
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
		Button segregationBtn = new ButtonBuilder().setText("Start Segregation Simulation")
									.setXLocation(50)
									.setYLocation(50)
									.build();
		
		Button predatorPreyBtn = new ButtonBuilder().setText("Start Predator-Prey Simulation")
									.setXLocation(50)
									.setYLocation(100)
									.build();
		
		Button fireBtn = new ButtonBuilder().setText("Start Fire Simulation")
									.setXLocation(50)
									.setYLocation(150)
									.build();
	
		Button gameOfLifeBtn = new ButtonBuilder().setText("Start Game-of-Life Simulation")
									.setXLocation(50)
									.setYLocation(200)
									.build();
		
		segregationBtn.setOnAction(goToSegregation);
		predatorPreyBtn.setOnAction(goToPredatorPrey);
		fireBtn.setOnAction(goToFire);
		gameOfLifeBtn.setOnAction(goToGameOfLife);
		
		root.getChildren().add(segregationBtn);
		root.getChildren().add(predatorPreyBtn);
		root.getChildren().add(fireBtn);
		root.getChildren().add(gameOfLifeBtn);
	}
}
