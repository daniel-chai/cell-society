package cellsociety_team07;

import javafx.scene.Node;

/**
 * This class represents a single Cell in a grid.
 */
public class Cell{

	public static final Cell OUT_OF_BOUNDS = new Cell(State.OUT_OF_BOUNDS);
	private CellView view;
	private State state;
	private Neighborhood neighborhood;
	
	/**
	 * Constructor for Cell class.
	 * @param state desired State of the Cell
	 */
	public Cell(State state) {
		this.state = state;
		
		this.view = new CellView();
		
		this.neighborhood = new Neighborhood();
	}
	
	/**
	 * @return the View representing the Cell
	 */
	public CellView getView() {
		return view;
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
	 * Sets the State of the cell
	 * @param state desired State of the Cell
	 */
	public void setState(State state) {
		this.state = state;
	}
}
