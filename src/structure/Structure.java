package structure;

import cellsociety_team07.Cell;
import java.awt.Point;
import java.util.Collection;
import java.util.ArrayList;

/**
 * This class represents the structure the Cellular Automata is ran on
 */
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
	
	/**
	 * Places a Cell at point p if that cell is in bounds
	 * @param cell the Cell that is being placed
	 * @param p the location the Cell is being placed
	 */
	public void addCell(Cell cell, Point p)
	{
		if(getCellFromPoint(p) != Cell.OUT_OF_BOUNDS)
		{
			board[p.x][p.y] = cell;
		}
	}
	
	private Cell getCellFromPoint(Point p)
	{
		return board[p.x][p.y];
	}
	
	private void defineOutOfBounds(Collection<Point> outOfBounds)
	{
		for(Point location : outOfBounds)
		{
			board[location.x][location.y] = Cell.OUT_OF_BOUNDS;
		}
	}
	
}
