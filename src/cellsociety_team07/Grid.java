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
	 * @param row the row of the Cell
	 * @param col the column of the Cell
	 * @return the Cell at the specified row and column
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
	
}
