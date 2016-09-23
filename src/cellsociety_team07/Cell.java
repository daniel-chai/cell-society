package cellsociety_team07;

import javafx.scene.shape.Rectangle;

/**
 * This class represents a single Cell in a grid.
 */
public class Cell {
	public static final double CELL_SIZE = 50;
	
	private Rectangle rectangle;
	private State state;
	private Neighborhood neighborhood;
	
	/**
	 * Constructor for Cell class.
	 * @param state desired State of the Cell
	 */
	public Cell(State state) {
		this.state = state;
		this.neighborhood = new Neighborhood();
		initRectangle();
	}
	
	private void initRectangle() {
		rectangle = new Rectangle();
		rectangle.setHeight(CELL_SIZE);
		rectangle.setWidth(CELL_SIZE);
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
