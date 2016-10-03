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
		return new CircleCellView(cell,cm);
	}
	
}
