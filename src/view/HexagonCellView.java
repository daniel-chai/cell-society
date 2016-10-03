package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;

/**
 * Creates a Cell with a Hexagon as the Node that represents it
 * @author Ryan Bergamini
 *
 */
public class HexagonCellView extends UpDownPolygonCellView
{
	/**
	 * Creates a new HexagonCellView
	 * @param cell- cell the View represents
	 * @param map - the map that defines the Color-State pairings
	 * @param isPointingUpwards - true if you want to create an upwards pointing hexagon node
	 */
	public HexagonCellView(Cell cell, ColorMap map, boolean isUp) 
	{
		super(cell, map, isUp);
	}

	@Override
	protected Polygon generateUpPolygon()
	{	
		Polygon hexagon = new Polygon(getWidth()/2.0,0,
				  getWidth(),getHeight()/3.0,
				  getWidth(),2*getHeight()/3.0,
				  getWidth()/2.0,getHeight(),
				  0.0,2*getHeight()/3.0,
				  0.0,getHeight()/3.0);
		hexagon.setLayoutY(-10);
		return hexagon;
	}

	@Override
	protected Polygon generateDownPolygon() 
	{
		Polygon hexagon = new Polygon(getWidth()/2.0,0,
									  getWidth(),getHeight()/3.0,
									  getWidth(),2*getHeight()/3.0,
									  getWidth()/2.0,getHeight(),
									  0.0,2*getHeight()/3.0,
									  0.0,getHeight()/3.0);
		hexagon.setLayoutY(10);
		return hexagon;
	}
	
}