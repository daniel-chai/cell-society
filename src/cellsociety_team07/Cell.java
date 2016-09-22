package cellsociety_team07;

import javafx.scene.shape.Rectangle;

/**
 * This class represents a single Cell in a grid.
 */
public class Cell {
	private Rectangle rectangle;
	private State state;
	private Neighborhood neighborhood;
	
	/**
	 * Constructor for Cell class.
	 * @param state desired State of the Cell
	 */
	public Cell(State state) {
		this.state = state;
		
		this.rectangle = new Rectangle();
		this.neighborhood = new Neighborhood();
	}
	
	/**
	 * @return the Rectangle representing the Cell
	 */
	public Rectangle getRectangle() {
		return rectangle;
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
