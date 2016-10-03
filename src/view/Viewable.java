package view;

import javafx.scene.Node;

/**
 * Class that model objects will display their graphics through
 */
public interface Viewable 
{
	public Node getNode();
	public void updateView();
}
