package cellsociety_team07;
import structure.Structure;

/**
 * This class represents a grid of Cells.
 */
public class Grid extends Structure
{
	private Cell[][] grid;
	
	/**
	 * Constructor for Grid class
	 * @param rows number of rows in the grid
	 * @param columns number of columns in the grid
	 */
	public Grid(int rows, int columns) {
		super(rows,columns);
	}
	
	/**
	 * @return the 2-D array of Cells representing the Grid
	 */
	public Cell[][] getGrid() {
		return grid;
	}
	
}
