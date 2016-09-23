package cellsociety_team07;

import javafx.scene.shape.Rectangle;

/**
 * This class represents a single Cell in a grid.
 */
public class Cell {
	public static final Cell OUT_OF_BOUNDS = new Cell(State.OUT_OF_BOUNDS);
	public static final double CELL_SIZE = 50;
	
	private CellView view;
	
	private Rectangle rectangle;
	private State state;
	private Neighborhood neighborhood;
	
	/**
	 * Constructor for Cell class.
	 * @param state desired State of the Cell
	 */
	public Cell(State state) {
		this.view = new CellView();
		
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
	 * @return the View representing the Cell
	 */
	public CellView getView() {
		return view;
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
}
