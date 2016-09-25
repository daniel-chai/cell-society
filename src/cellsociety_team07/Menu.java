package cellsociety_team07;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * This class represents the Menu Scene from where a simulation can be started.
 */
public class Menu {
	private SceneManager sceneManager;
	private Scene menuScene;
	private Group root;
	
	/**
	 * Constructor for Menu class
	 * @param sceneManager SceneManager currently being used
	 */
	public Menu(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
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
		addDataInputButton();
		
		return menuScene;
	}
	
	private void addSimulationStartButtons() {
		Button segregationBtn = UIGenerator.createButton("Start Segregation Simulation", 50, 50);
		segregationBtn.setOnAction(e -> sceneManager.goToSegregationScene(sceneManager));
		
		Button predatorPreyBtn = UIGenerator.createButton("Start Predator-Prey Simulation", 50, 100);
		predatorPreyBtn.setOnAction(e -> sceneManager.goToPredatorPreyScene(sceneManager));
		
		Button fireBtn = UIGenerator.createButton("Start Fire Simulation", 50, 150);
		fireBtn.setOnAction(e -> sceneManager.goToFireScene(sceneManager));
		
		Button gameOfLifeBtn = UIGenerator.createButton("Start Game-of-Life Simulation", 50, 200);
		gameOfLifeBtn.setOnAction(e -> sceneManager.goToGameOfLifeScene(sceneManager));
		
		root.getChildren().add(segregationBtn);
		root.getChildren().add(predatorPreyBtn);
		root.getChildren().add(fireBtn);
		root.getChildren().add(gameOfLifeBtn);
	}
	
	private void addDataInputButton() {
		Button dataInputButton = UIGenerator.createButton("Input XML Data", 50, 300);
		dataInputButton.setOnAction(e -> sceneManager.goToDataInputScene(sceneManager));
		
		root.getChildren().add(dataInputButton);
	}
}
