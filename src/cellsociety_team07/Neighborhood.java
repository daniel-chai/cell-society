package cellsociety_team07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the neighboring Cells of a particular Cell.
 */
public class Neighborhood {
	private List<Cell> neighbors;
	
	/**
	 * Constructor for Neighborhood class
	 */
	public Neighborhood() {
		neighbors = new ArrayList<Cell>();
	}
	
	/**
	 * @return the list of Cells representing the Neighborhood
	 */
	public List<Cell> getNeighbors() {
		return Collections.unmodifiableList(neighbors);
	}
	
	/**
	 * Adds a Cell to the Neighborhood
	 * @param neighbor the Cell to add
	 */
	public void addNeighbor(Cell neighbor) {
		neighbors.add(neighbor);
	}
	
	/**
	 * Removes a Cell from the Neighborhood
	 * @param neighbor the Cell to remove
	 */
	public void removeNeighbor(Cell neighbor) {
		neighbors.remove(neighbor);
	}
}
