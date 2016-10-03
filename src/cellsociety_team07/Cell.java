package cellsociety_team07;

import neighborhood.Neighborhood;

/**
 * This class represents a single Cell in a grid.
 * 
 * @author Daniel Chai
 */

public class Cell {
	public static final Cell OUT_OF_BOUNDS = new Cell(State.OUT_OF_BOUNDS);
	public static final double CELL_SIZE = 50;

	private State state;
	private Neighborhood neighborhood;
	
	
	/**
	 * Constructor for Cell class.
	 * @param state desired State of the Cell
	 */
	public Cell(State state) {
		this.state = state;
		this.neighborhood = new Neighborhood();
	}
	
	
	/**
	 * This is a frequent test used by other classes,
	 * @param cell- the Cell being tested
	 * @return true if the Cell is not null or OUT_OF_BOUNDS
	 */
	public boolean isValid()
	{
		return !this.equals(Cell.OUT_OF_BOUNDS);
	}
	
	/**
	 * @return the current State of the Cell
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * @return the Neighborhood of the Cell
	 */
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}
	
	/**
	 * Sets the State of the cell, checks to make sure the Cell is
	 * not an OUT_OF_BOUNDS cell by checking the State. The if statement
	 * makes sure the Cell OUT_OF_BOUNDS constant is immutable
	 * @param state desired State of the Cell
	 */
	public void setState(State state) {
		if(!this.state.equals(State.OUT_OF_BOUNDS))
		{
			this.state = state;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cell)) {
			return false;
		}
		else {
			Cell other = (Cell) obj;
			return this.state.equals(other.state);
		}
	}
}
