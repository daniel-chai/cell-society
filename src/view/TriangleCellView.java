package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Creates a Cell with a Triangle as the Node that represents it
 * @author Ryan Bergamini
 *
 */
public class TriangleCellView extends CellView
{
	private int width;
	private int height;
	private Polygon triangle;
	private boolean isPointingUpwards = true; // Default value, Allows us to call updateTriangle() in the initShapeMethod
	
	/**
	 * Creates a new TriangleCellView
	 * @param cell- cell the View represents
	 * @param map - the map that defines the Color-State pairings
	 * @param isPointingUpwards - true if you want to create an upwards pointing triangle node
	 */
	public TriangleCellView(Cell cell, ColorMap map, boolean isPointingUpwards) 
	{
		super(cell, map);
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.isPointingUpwards = isPointingUpwards;
		
		triangle = updateTriangle();
	}
	
	/**
	 * Sets the view to represent a triangle pointing upwards
	 */
	public void setPointingUpwards()
	{
		isPointingUpwards = true;
	}
	
	/**
	 * Sets the view to represent a triangle pointing downwards
	 */
	public void setPointingDownwards()
	{
		isPointingUpwards = false;
	}
	
	@Override
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		triangle = updateTriangle();
	}

	@Override
	public Node getNode()
	{
		updateView();
		return triangle;
	}
	

	@Override
	protected Shape initShape() 
	{
		return updateTriangle();
	}
	
	@Override
	protected void setColor(Color c)
	{
		triangle.setFill(c);
	}
	

	private Polygon updateTriangle()
	{

		if(isPointingUpwards)
		{
			return new Polygon(0.0,height,width,height,width/2.0,0.0);
		}
		else
		{
			return new Polygon(0.0, 0.0,width, 0.0,width/2.0,height);
		}
	}
	

}
