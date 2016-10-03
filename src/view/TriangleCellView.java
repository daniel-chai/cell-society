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
public class TriangleCellView extends UpDownPolygonCellView
{
	
	public TriangleCellView(Cell cell, ColorMap map, int row, int col) 
	{
		super(cell, map, isSumOfCoordinatesEven(row,col));
	}

	@Override
	protected Polygon generateUpPolygon() 
	{
		return  new Polygon(0.0,getHeight(),getWidth(),getHeight(),getWidth()/2.0,0.0);
	}

	@Override
	protected Polygon generateDownPolygon() 
	{
		return new Polygon(0.0, 0.0,getWidth(), 0.0,getWidth()/2.0,getHeight());
	}
	
	
	private static boolean isSumOfCoordinatesEven(int row, int col)
	{
		return (row + col) % 2 == 0;
	}
	

}
