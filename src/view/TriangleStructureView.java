package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import structure.TriangleStructure;


public class TriangleStructureView extends StructureView
{
	public TriangleStructureView(TriangleStructure triangle, ColorMap cm, int width, int height)
	{
		super(triangle,cm,width,height);
	}

	@Override
	protected CellView getCellView(Cell cell, int row, int col, ColorMap cm) 
	{
		if(isSumOfCoordinatesEven(row,col))
		{
			return new TriangleCellView(cell,cm,false);
		}
		else
		{
			return new TriangleCellView(cell,cm,true);
		}
	}
	
	private boolean isSumOfCoordinatesEven(int row, int col)
	{
		return (row + col) % 2 == 0;
	}
	
	
	
}
