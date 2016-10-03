package cellsociety_team07;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.Map;

/**
 * This class contains the corresponding View for the cell
 */
public class CellView implements Viewable
{
	private Shape view;
	private ColorMap colorMap;
	private Cell cell;
	private static final int DEFAULT_WIDTH = 10; 
	private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH;
	/**
	 * Creates a new CellView with a Circle Node
	 */
	public CellView(Cell c, ColorMap m)
	{
		this.view = new Circle();
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		this.cell = c;
		this.colorMap = m;
		updateView();
	}
	
	/**
	 * Called upon to update the view, checks the Cell for its state and sets it to the 
	 * correct corresponding color
	 */
	public void updateView()
	{
		setColor(colorMap.getColor(cell.getState()));
	}
	
	/**
	 * Method that sets the width and height of the Node to width and height
	 * @param width the width the Node is adjusted to
	 * @param height the height the Node is adjusted to
	 */
	public void setSize(int width, int height)
	{
		((Circle)view).setRadius(Math.min(width, height));
		//((Rectangle)view).setWidth(width);
		//((Rectangle)view).setHeight(height);
	}
	
	/**
	 * Returns the javafx Node of the view
	 */
	public Node getNode()
	{
		return view;
	}
	
	private void setColor(Color c)
	{
		view.setFill(c);
	}
}
