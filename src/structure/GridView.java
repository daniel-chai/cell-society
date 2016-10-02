package structure;

import cellsociety_team07.Grid;
import cellsociety_team07.ColorMap;

/**
 * Creates a View for the Grid class. This class follows the protocol that
 * different types of Structures will require different types of Views.
 * @author Ryan Bergamini
 *
 */
public class GridView extends StructureView
{
	public GridView(Grid grid, ColorMap cm, int width, int height)
	{
		super(grid,cm,width,height);
	}
}
