package view;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Map;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;

/**
 * This class contains the corresponding View for the cell
 */
public abstract class CellView implements Viewable
{
	private Shape view;
	private ColorMap colorMap;
	private Cell cell;
	protected static final int DEFAULT_WIDTH = 10; 
	protected static final int DEFAULT_HEIGHT = DEFAULT_WIDTH;
	/**
	 * Creates a new CellView with a Circle Node
	 */
	public CellView(Cell c, ColorMap m)
	{
		this.view = initShape();
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
	 * Returns the javafx Node of the view
	 */
	public Node getNode()
	{
		return view;
	}
	
	/**
	 * Sets the color of the Node to c.
	 * @param c - the desired color
	 */
	protected void setColor(Color c)
	{
		view.setFill(c);
	}
	

	/**
	 * Method that sets the width and height of the Node to width and height
	 * @param width the width the Node is adjusted to
	 * @param height the height the Node is adjusted to
	 */
	abstract public void setSize(int width, int height);
	
	/**
	 * Returns the Shape node that represents a cell
	 * @return the Shape node that represents a cell
	 */
	abstract protected Shape initShape();

}
