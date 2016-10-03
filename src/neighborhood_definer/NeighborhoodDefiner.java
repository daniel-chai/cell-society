package neighborhood_definer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A NeighborhoodDefiner creates an object that defines the neighborhood for a cell. The concept of a neighborhood varies between type
 * of Structure. For example, in a RectangleStructrue, the definition of a neighborhood is not dependent on a point of a cell in a board
 * while in a TriangleStructure and a HexagonStructure the neighborhood is dependent.
 * 
 * @author Ryan Bergamini (rb265)
 *
 */
public abstract class NeighborhoodDefiner 
{
	/**
	 * Given a list of displacements and a central point, this method returns a list of the potential points
	 * @param displacements- Collection of Points defined relative to the center
	 * @param center- The point the displacements are defined from
	 * @return- A list of the absolute values of each point
	 */
	protected Collection<Point> calculatePointsFromDisplacements(Collection<Point> displacements, Point center)
	{
		ArrayList<Point> potentialPoints = new ArrayList<Point>();
		
		for(Point point : displacements)
		{
			potentialPoints.add(new Point(center.x + point.x, center.y + point.y));
		}
		
		return potentialPoints;
	}
	
	public abstract Collection<Point> getPotentialNeighbors(Point point);
}
