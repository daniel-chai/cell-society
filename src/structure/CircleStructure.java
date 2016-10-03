package structure;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public class CircleStructure extends Structure
{
	public CircleStructure(int radius)
	{
		// 3 and 2 are not the magic numbers, they are adjusted to contain the contents of the HexGrid
		super(circlealHeightOffset(radius),circlealWidthOffset(radius),getOutOfBoundsPoints(radius));
	}

	private static int circlealWidthOffset(int radius) {
		return 3*radius - 2;
	}


	private static int circlealHeightOffset(int radius) {
		return 3*radius - 2;
	}
	
	private static Collection<Point> getOutOfBoundsPoints(int radius)
	{
		ArrayList<Point> outOfBounds = new ArrayList<Point>();
		
		for(int row = 0; row < circlealHeightOffset(radius); row++)
		{
			for(int col = 0; col < circlealWidthOffset(radius); col++)
			{
				if(!isInCircle(radius,row,col))
				{
					outOfBounds.add(new Point(row,col));
				}
			}
		}
		
		return outOfBounds;
	}
	
	private static boolean isInCircle(int radius, int row, int col)
	{	
		int radiusSquared = (radius)*(radius) + (radius/2)*(radius/2);
		int pointDistance = (row-radius)*(row-radius) + (col-radius)*(col-radius);
		
		return radiusSquared > pointDistance;
	}
}
