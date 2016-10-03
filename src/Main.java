

import cellsociety_team07.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point to the application.
 * 
 * @author Daniel Chai
 */
public class Main extends Application {
	public static final String TITLE = "CellSociety";
	public static final int SIZE = 700;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(TITLE);
	    new SceneManager(primaryStage);
	}
	    
	public static void main(String[] args) {
		launch(args);
	}
}
