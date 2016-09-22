package structure;

import cellsociety_team07.Cell;
import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This class represents the structure the Cellular Automata is ran on
 * StructureIterator class based on code from: http://codereview.stackexchange.com/questions/48109/simple-example-of-an-iterable-and-an-iterator-in-java
 */
public class Structure implements Iterable<Cell>
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
	
	/**
	 * Returns the width of the board
	 * @return the width of the board
	 */
	public int getWidth()
	{
		return board[0].length;
	}
	
	/**
	 * Returns the height of the board
	 * @return the height of the board
	 */
	public int getHeight()
	{
		return board.length;
	}
	
	@Override
	/**
	 * Returns an iterator to loop through all the Cells in a structure
	 */
	public Iterator<Cell> iterator() 
	{
		return new StructureIterator(board);
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
	
	/**
	 * @return a copy of the board class so that other classes can't change its data
	 */
	private Cell[][] getCopyOfBoard()
	{
		Cell[][] copyOfBoard = new Cell[board.length][board[0].length];
		
		for(int i = 0; i < copyOfBoard[0].length; i++)
		{
			copyOfBoard[i] = Arrays.copyOf(board[i], board[i].length);
		}
		
		return copyOfBoard;	
	}
	
	
	private class StructureIterator implements Iterator<Cell>
	{
		private int index; // Begins at 0
		private Cell[][] board;
		private final int LAST_INDEX;
		
		public StructureIterator(Cell[][] board)
		{
			this.index = 0;
			this.board = board;
			this.LAST_INDEX = board.length * board[0].length - 1;
		}
		
		public boolean hasNext()
		{
			return index < LAST_INDEX;
		}
		
		public Cell next()
		{
			if(this.hasNext())
			{
				index++;
				Point cell = getCoordinate(index);
				return board[cell.x][cell.y];
			}
			throw new NoSuchElementException();
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
		private Point getCoordinate(int value)
		{
			return new Point(value / board.length , value % board.length);
		}
	}
	
}
