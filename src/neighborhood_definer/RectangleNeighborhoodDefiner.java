package neighborhood_definer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import cellsociety_team07.Cell;
import neighborhood.Neighborhood;

public class RectangleNeighborhoodDefiner extends NeighborhoodDefiner
{
	public static final Collection<Point> SURROUNDING_POINTS_COLLECTION = Collections.unmodifiableCollection(generateSurroundingPointsCollection());
	public static final Collection<Point> CROSS_POINTS_COLLECTION = Collections.unmodifiableCollection(generateCrossPointsCollection());
	
	private Collection<Point> pointDisplacements;
	
	/**
	 * Creates a Neighborhood object for a RectangularGrid where the location of the neighbors (relative to the locationof a cell)
	 * is defined in the Collection<Point> pointDisplacements
	 * @param pointDisplacements- List of points in the neighborhood defined by their distance to a given cell
	 */
	public RectangleNeighborhoodDefiner(Collection<Point> pointDisplacements)
	{
		this.pointDisplacements = pointDisplacements;
	}
	@Override
	public Collection<Point> getPotentialNeighbors(Point point) 
	{
		return calculatePointsFromDisplacements(pointDisplacements,point);
	}
	
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
}
