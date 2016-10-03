package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import structure.TriangleStructure;


public class TriangleStructureView extends StructureView
{
	public TriangleStructureView(TriangleStructure triangle, ColorMap cm, int width, int height, String cellViewType)
	{
		super(triangle,cm,width,height,cellViewType);
	}
	
	public TriangleStructureView(TriangleStructure triangle, ColorMap cm, int width, int height)
	{
		super(triangle,cm,width,height,CellView.TRIANGLE);
	}
}
