package cellsociety_team07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;
import java.awt.Point;

/**
 * This class represents the neighboring Cells of a particular Cell.
 * 
 * @author Daniel Chai
 */
public class Neighborhood {
	private List<Cell> neighbors;
	
	public static final Collection<Point> RECTANGLE_SURROUNDING_POINTS_COLLECTION = Collections.unmodifiableCollection(generateSurroundingPointsCollection());
	public static final Collection<Point> RECTANGLE_CROSS_POINTS_COLLECTION = Collections.unmodifiableCollection(generateCrossPointsCollection());
	
	private static Collection<Point> generateSurroundingPointsCollection()
	{
		ArrayList<Point> neighborDisplacements = new ArrayList<Point>();
		
		for(int i = -1; i <= 1; i++)
		{
			for(int j = -1; j <= 1; j++)
			{
				if(i == 0 && i == j)
				{
					continue;
				}
				neighborDisplacements.add(new Point(i,j));
			}
		}
		
		return neighborDisplacements;
	}
	
	private static Collection<Point> generateCrossPointsCollection()
	{
		ArrayList<Point> neighborDisplacements = new ArrayList<Point>();
		neighborDisplacements.add(new Point(1,0));
		neighborDisplacements.add(new Point(-1,0));
		neighborDisplacements.add(new Point(0,1));
		neighborDisplacements.add(new Point(0,-1));
		
		return neighborDisplacements;
	}
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
