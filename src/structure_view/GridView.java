package structure_view;

import cellsociety_team07.ColorMap;
import structure.RectangleStructure;

/**
 * Creates a View for the Grid class. This class follows the protocol that
 * different types of Structures will require different types of Views.
 * @author Ryan Bergamini
 *
 */
public class GridView extends StructureView
{
	public GridView(RectangleStructure grid, ColorMap cm, int width, int height)
	{
		super(grid,cm,width,height);
	}
}