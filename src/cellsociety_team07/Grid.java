package cellsociety_team07;

import java.awt.Point;

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
	 * @param row the row of the Cell
	 * @param col the column of the Cell
	 * @return the Cell at the specified row and column
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
	
	/**
	 * @param cell the Cell
	 * @return location of the specified Cell in the grid
	 */
	public Point getLocation(Cell cell) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == cell) {
					return new Point(row, col);
				}
			}
		}
		
		return null;
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
