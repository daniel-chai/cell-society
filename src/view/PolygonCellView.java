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
public abstract class PolygonCellView extends CellView
{
	private int width;
	private int height;
	private Polygon polygon;
	
	/**
	 * Creates a new TriangleCellView
	 * @param cell- cell the View represents
	 * @param map - the map that defines the Color-State pairings
	 * @param isPointingUpwards - true if you want to create an upwards pointing triangle node
	 */
	public PolygonCellView(Cell cell, ColorMap map) 
	{
		super(cell, map);
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		
		polygon = updatePolygon();
	}
	
	@Override
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		polygon = updatePolygon();
	}

	@Override
	public Node getNode()
	{
		updateView();
		return polygon;
	}
	

	@Override
	protected Shape initShape() 
	{
		return updatePolygon();
	}
	
	@Override
	/**
	 * Sets the color of the Polygon to c
	 */
	protected void setColor(Color c)
	{
		polygon.setFill(c);
	}
	
	/**
	 * Allows subclasses to create Polygons based on width
	 * @return width of CellView
	 */
	protected int getWidth()
	{
		return width;
	}
	
	/**
	 * Allows subclasses to create Polygons based on height
	 * @return height of CellView
	 */
	protected int getHeight()
	{
		return height;
	}
	
	

	abstract protected Polygon updatePolygon();
}
	