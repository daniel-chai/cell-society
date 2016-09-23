package structure;

import cellsociety_team07.Cell;
import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javafx.scene.Node;

/**
 * This class represents the structure the Cellular Automata is ran on
 * StructureIterator class based on code from: http://codereview.stackexchange.com/questions/48109/simple-example-of-an-iterable-and-an-iterator-in-java
 */
public class Structure implements Iterable<Cell>
{
	private Cell[][] board;
	private StructureView view;
	
	/**
	 * Convenience constructor that creates a width*height board with no bounds
	 * @param width
	 * @param height
	 */
	public Structure(int width,int height)
	{
		this(width,height,new ArrayList<Point>());
		view = new StructureView(board);
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
	// I DON'T THINK THIS CODE BELONGS HERE-----------------START----
	public Node generateView(int width,int height)
	{
		view.setSizeOfView(width, height);
		return view.getNode();
	}
	
	// I DON't THINK THIS CODE BELONGS HERE------------------END-----
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
	 * Convenience method to add a cell
	 * @param cell - the Cell that is being placed
	 * @param i - the x coordinate (typically the height in a board)
	 * @param j = the y coordinate (typically the width in a board)
	 */
	public void addCell(Cell cell, int i, int j)
	{
		addCell(cell,new Point(i,j));
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
	
	/**
	 * Returns Cell at (i,j)
	 * @param i
	 * @param j
	 */
	public Cell getCell(int i, int j)
	{
		return getCellFromPoint(new Point(i,j));
	}
	
	/**
	 * Retrieves a Cell from a specific point
	 * @param p point the Cell is located at
	 * @return the Cell at point p
	 */
	public Cell getCellFromPoint(Point p)
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
