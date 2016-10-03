package structure;

import cellsociety_team07.Cell;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Point;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.Node;
import neighborhood_definer.NeighborhoodDefiner;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Collections;

/**
 * This class represents the structure the Cellular Automata is ran on
 * StructureIterator class based on code from: http://codereview.stackexchange.com/questions/48109/simple-example-of-an-iterable-and-an-iterator-in-java
 */
public abstract class Structure implements Iterable<Cell>
{
	private Map<Point,Cell> board;
	private int numCols;
	private int numRows;
	private boolean isBounded;
	
	/**
	 * Convenience constructor that creates a width*height board with no bounds
	 * @param numCols
	 * @param numRows
	 */
	public Structure(int numRows,int numCols)
	{
		this(numRows,numCols,new ArrayList<Point>());
	}
	
	/**
	 * This is the default constructor
	 * @param numCols - The width of the board
	 * @param numRows - The height of the board
	 * @param outOfBounds - Locations that cells
	 */
	public Structure(int numRows, int numCols, Collection<Point> outOfBounds)
	{
		this.board = new HashMap<Point,Cell>();
		this.numCols = numCols;
		this.numRows = numRows;
		
		defineOutOfBounds(outOfBounds);
		fillNotOutOfBoundsWithNulls();
	}
	
	/**
	 * Places a Cell at point p if that cell is in bounds
	 * @param cell the Cell that is being placed
	 * @param p the location the Cell is being placed
	 */
	public void addCell(Cell cell, Point point)
	{
		Cell currentCellAtPoint = getCell(point);
		if(currentCellAtPoint == null || !currentCellAtPoint.equals(Cell.OUT_OF_BOUNDS))
		{
			board.put(point, cell);
		}
	}
	
	/**
	 * Convenience method to add a cell
	 * @param cell - the Cell that is being placed
	 * @param i - the x coordinate (typically the height in a board)
	 * @param j = the y coordinate (typically the width in a board)
	 */
	public void addCell(Cell cell, int row, int col)
	{
		addCell(cell,new Point(row,col));
	}
	
	/**
	 * Returns the width of the board
	 * @return the width of the board
	 */
	public int getWidth()
	{
		return numCols;
	}
	
	/**
	 * Returns the height of the board
	 * @return the height of the board
	 */
	public int getHeight()
	{
		return numRows;
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
	public Cell getCell(int row, int col)
	{
		return getCell(new Point(row,col));
	}
	
	/**
	 * Retrieves a Cell from a specific point
	 * @param p point the Cell is located at
	 * @return the Cell at point p
	 */
	public Cell getCell(Point p)
	{
		return board.get(p);
	}
	
	/**
	 * This method will return Out of Bounds and Valid points
	 * @return an immutable list of the Points on the board
	 */
	public Set<Point> getAllPoints()
	{
		return Collections.unmodifiableSet(board.keySet());
	}
	
	/**
	 * This method only returns valid points on the board
	 * @return collection of valid points on the board
	 */
	public Collection<Point> getPointsOnBoard()
	{
		ArrayList<Point> pointsOnBoard = new ArrayList<Point>();
		
		for(Point point : getAllPoints())
		{
			Cell cellAtPoint = getCell(point);
			if(cellAtPoint == null || !cellAtPoint.equals(Cell.OUT_OF_BOUNDS))
			{
				pointsOnBoard.add(point);
			}
		}
		
		return pointsOnBoard;
	}
	
	/**
	 * Defines the neighborhood of the cells in the grid based on how the
	 * simulation defines the neighbors collection. The calculateNeighbors
	 * method ensures that the neighbors that are passed in does not contain
	 * (0,0).
	 * @param neighbors: Collection of points that are defined as relative
	 * to (0,0), the location of the cell. For example, if you wanted to 
	 * define the neighborhoods of the Cells in the Structure to be immediately
	 * next to the Cell, you would pass a Collection of Points containing:
	 * (-1,0),(-1,1),(0,1),(1,1),(1,1),(1,-1),(0,-1),(-1,-1)
	 */
	public void calculateNeighborsForCells(NeighborhoodDefiner neighborDefiner)
	{
		for(Point cellPoint : getPointsOnBoard())
		{
			Cell currentCell = getCell(cellPoint);
			
			if(currentCell != null && currentCell.isValid())
			{
				Collection<Point> neighbors = neighborDefiner.getPotentialNeighbors(cellPoint);
				
				for(Point possiblePoint : neighbors)
				{
					if(isValidCellAt(possiblePoint))
					{
						currentCell.getNeighborhood().addNeighbor(getCell(possiblePoint));
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param p
	 * @return true if the cell is in the board, that cell is not null, p is not out of bounds
	 */
	private boolean isValidCellAt(Point p)
	{
		if(isBounded && p.x >= numCols && p.y >= numRows)
		{
			return false;
		}
		
		if(board.containsKey(p))
		{
			Cell cell = getCell(p);
			return cell != null && cell.isValid();
		}
		
		return false;
	}
	
	/**
	 * @param cell the Cell
	 * @return location of the specified Cell in the grid
	 */
	public Point getLocation(Cell cell) {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (board.get(new Point(row,col))== cell) {
					return new Point(row, col);
				}
			}
		}
		
		return null;
	}
	

	private void fillNotOutOfBoundsWithNulls()
	{
		for(int row = 0; row < getHeight(); row++)
		{
			for(int col = 0; col < getWidth(); col++)
			{
				Point point = new Point(row,col);
				
				if(!board.containsKey(point) || !getCell(point).equals(Cell.OUT_OF_BOUNDS))
				{
					board.put(point,null);
				}
			}
		}
	}
	

	private void defineOutOfBounds(Collection<Point> outOfBounds)
	{
		for(Point location : outOfBounds)
		{
			board.put(location,Cell.OUT_OF_BOUNDS);
		}
	}
	
	private class StructureIterator implements Iterator<Cell>
	{
		private ArrayList<Cell> validCells;
		
		/**
		 * Takes in the board that Structure uses and returns an Iterator that goes through
		 * all valid cells
		 * @param board
		 */
		public StructureIterator(Map<Point,Cell> board)
		{
			this.validCells = removeOutOfBounds(board.values());
		}
		
		private ArrayList<Cell> removeOutOfBounds(Collection<Cell> values)
		{
			ArrayList<Cell> outOfBounds = new ArrayList<Cell>();
			outOfBounds.add(Cell.OUT_OF_BOUNDS);
			values.removeAll(outOfBounds);
			
			return new ArrayList<Cell>(values);
		}
		
		public boolean hasNext()
		{
			return validCells.size() > 0;
		}
		
		public Cell next()
		{
			if(this.hasNext())
			{
				return validCells.remove(0);
			}
			throw new NoSuchElementException();
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
}
