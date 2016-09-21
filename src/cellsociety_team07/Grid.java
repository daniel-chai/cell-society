package cellsociety_team07;

/**
 * This class represents a grid of Cells.
 */
public class Grid {
	private Cell[][] grid;
	
	/**
	 * Constructor for Grid class
	 * @param rows number of rows in the grid
	 * @param columns number of columns in the grid
	 */
	public Grid(int rows, int columns) {
		grid = new Cell[rows][columns];
	}
	
	/**
	 * @return the 2-D array of Cells representing the Grid
	 */
	public Cell[][] getGrid() {
		return grid;
	}
	
	/**
	 * Adds a Cell to the grid
	 * @param cell the Cell to add
	 * @param row the row to place the Cell in
	 * @param col the column to place the Cell in
	 */
	public void addCell(Cell cell, int row, int col) {
		grid[row][col] = cell;
	}
}
