package neighborhood_definer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TriangleNeighborhoodDefiner extends NeighborhoodDefiner
{
	private static final Collection<Point> EVEN_SURROUNDING_POINTS_COLLECTION = Collections.unmodifiableCollection(generateEvenSurroundingPointsCollection());
	private static final Collection<Point> ODD_SURROUNDING_POINTS_COLLECTION = Collections.unmodifiableCollection(generateOddSurroundingPointsCollection());

	@Override
	public Collection<Point> getPotentialNeighbors(Point point) 
	{
		if(isCoordinateSumEven(point))
		{
			return calculatePointsFromDisplacements(EVEN_SURROUNDING_POINTS_COLLECTION, point);
		}
		else
		{
			return calculatePointsFromDisplacements(ODD_SURROUNDING_POINTS_COLLECTION, point);
		}
	}
	
	private boolean isCoordinateSumEven(Point point)
	{
		return (point.x + point.y) % 2 == 0;
	}
	
	private static Collection<Point> generateOddSurroundingPointsCollection() 
	{
		ArrayList<Point> neighborDisplacements = new ArrayList<Point>();
		neighborDisplacements.add(new Point(1,0));
		neighborDisplacements.add(new Point(0,1));
		neighborDisplacements.add(new Point(0,-1));
		
		return neighborDisplacements;
	}

	private static Collection<Point> generateEvenSurroundingPointsCollection() 
	{
		ArrayList<Point> neighborDisplacements = new ArrayList<Point>();
		neighborDisplacements.add(new Point(-1,0));
		neighborDisplacements.add(new Point(0,1));
		neighborDisplacements.add(new Point(0,-1));
		
		return neighborDisplacements;
	}

}
