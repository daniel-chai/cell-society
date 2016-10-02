package cellsociety_team07;

import java.util.Collection;
import java.awt.Point;
import java.util.ArrayList;

import structure.Structure;

public class TriangleGrid extends Structure
{
	public TriangleGrid(int height)
	{
		super(height,calculateWidth(height),getOutOfBoundsPoints(height));
	}
	
	private static int calculateWidth(int height)
	{
		return 2*height - 1;
	}
	
	private static Collection<Point> getOutOfBoundsPoints(int height)
	{
		ArrayList<Point> points = new ArrayList<Point>();
		
		int width = calculateWidth(height);
		
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				if(!isInTriangle(width,height,row,col))
				{
					points.add(new Point(row,col));
				}
			}
		}
		
		return points;
		
	}
	
	private static boolean isInTriangle(int width, int height, int row, int col)
	{
		int midpoint = width/2;
		
		return (col >= (midpoint-row) && col <= (midpoint+row));
	}
}
