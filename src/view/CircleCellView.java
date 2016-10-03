package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;

public class CircleCellView extends CellView
{

	public CircleCellView(Cell cell, ColorMap map) 
	{
		super(cell, map);
	}

	@Override
	protected Shape initShape() {
		return new Circle();
	}

	@Override
	public void setSize(int width, int height) 
	{
		((Circle)getNode()).setRadius(Math.min(width, height));
	}
	
}
