package cellsociety_team07;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This class contains the corresponding View for the cell
 */
public class CellView implements Viewable
{
	private Shape view;
	private static final int WIDTH = 10; // Regardless of the Shape, the Width and the Height of the view will be equal
	
	/**
	 * Creates a new CellView with a Circle Node
	 */
	public CellView()
	{
		view = new Circle(WIDTH/2);
	}
	
	/**
	 * Creates a new CellView with the Color c
	 * @param c
	 */
	public CellView(Color c)
	{
		this();
		setColor(c);
	}
	
	/**
	 * Set the color of the Shape
	 * @param c the color view is set to
	 */
	public void setColor(Color c)
	{
		view.setFill(c);
	}
	
	/**
	 * Method that sets the width of the Node to width
	 * NOTE: Height will be set to be equivalent to width
	 * @param width the width the Node is adjusted to
	 */
	public void setWidth(int width)
	{
		((Circle)view).setRadius(width/2);
	}
	
	public Node getNode()
	{
		return view;
	}
}
