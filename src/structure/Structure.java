package structure;

import cell.Cell;
import java.awt.Point;
import java.util.Collection;
import java.util.ArrayList;

public class Structure 
{
	private Cell[][] board;
	
	/**
	 * Convenience constructor that creates a width*height board with no bounds
	 * @param width
	 * @param height
	 */
	public Structure(int width,int height)
	{
		this(width,height,new ArrayList<Point>());
	}
	
	/**
	 * This is the default constructor
	 * @param width - The width of the board
	 * @param height - The height of the board
	 * @param outOfBounds - Locations that cells
	 */
	public Structure(int width, int height, Collection<Point> outOfBounds)
	{
		board = new Cell[width][height];
		defineOutOfBounds(outOfBounds);
	}
	
	private void defineOutOfBounds(Collection<Point> outOfBounds)
	{
		for(Point location : outOfBounds)
		{
			board[location.x][location.y] = Cell.OUT_OF_BOUNDS;
		}
	}
	
}
