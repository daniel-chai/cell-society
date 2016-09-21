package cellsociety_team07;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point to the application.
 */
public class Main extends Application {
	public static final String TITLE = "CellSociety";
	public static final int SIZE = 600;
		
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(TITLE);
	    	
	    SceneManager sceneManager = new SceneManager(primaryStage);
	    sceneManager.goToMenuScene(sceneManager);
	}
	    
	public static void main(String[] args) {
		launch(args);
	}
}
