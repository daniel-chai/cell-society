package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import structure.HexagonStructure;

public class HexagonStructureView extends StructureView
{
	public HexagonStructureView(HexagonStructure hexagon, ColorMap cm, int width, int height)
	{
		super(hexagon,cm,width,height);
	}

	@Override
	protected CellView getCellView(Cell cell, int row, int col, ColorMap cm) 
	{
		if(isEven(col))
		{
			return new HexagonCellView(cell,cm,true);
		}
		else
		{
			return new HexagonCellView(cell,cm,false);
		}
	}
	
	private boolean isEven(int col)
	{
		return col % 2 == 0;
	}
}
