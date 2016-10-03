package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class RectangleCellView extends CellView
{

	public RectangleCellView(Cell cell, ColorMap map) 
	{
		super(cell, map);
	}

	@Override
	public void setSize(int width, int height)
	{
		((Rectangle)getNode()).setWidth(width);
		((Rectangle)getNode()).setHeight(height);
	}

	@Override
	protected Shape initShape() 
	{
		return new Rectangle();
	}

}
