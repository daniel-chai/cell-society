package cellsociety_team07;

/**
 * This class represents a single Cell in a grid.
 */
public class Cell {
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
	 * @return the current State of the Cell
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Sets the State of the cell
	 * @param state desired State of the Cell
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * @return the Neighborhood of the Cell
	 */
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}
}
