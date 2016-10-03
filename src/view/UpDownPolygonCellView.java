package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import javafx.scene.shape.Polygon;

/**
 * This class is for PolygonCellView that have alternating up and down positions depending
 * on the location in the grid. For example, for a TriangleStructureView, when the sum of the
 * coordinates is odd, the cell is represented as an upwards pointing triangle and when
 * the sume of the coordinates is evene, the cell is represented as a downwards pointing triangle.
 * @author Ryan Bergamini
 *
 */
public abstract class UpDownPolygonCellView extends PolygonCellView
{
	private boolean isUp = true; // Set the default value to allow updatePolygon() to function when initShape() is called

	public UpDownPolygonCellView(Cell cell, ColorMap map, boolean isUp) 
	{
		super(cell, map);
		this.isUp = isUp;
	}

	@Override
	protected Polygon updatePolygon() 
	{
		if(isUp)
		{
			return generateUpPolygon();
		}
		else
		{
			return generateDownPolygon();
		}
	}
	
	abstract protected Polygon generateUpPolygon();
	abstract protected Polygon generateDownPolygon();

}
