package structure;

import javafx.scene.Node;
import java.awt.Point;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Creates a Hexagon shaped grid
 * @author Ryan Bergamini
 */
public class HexagonStructure extends Structure
{
	
	public HexagonStructure(int sideLength)
	{
		// 3 and 2 are not the magic numbers, they are adjusted to contain the contents of the HexGrid
		super(hexagonalHeightOffset(sideLength),hexagonalWidthOffset(sideLength),getOutOfBoundsPoints(sideLength));
	}

	private static int hexagonalWidthOffset(int sideLength) {
		return 3*sideLength - 2;
	}


	private static int hexagonalHeightOffset(int sideLength) {
		return 2*sideLength - 1;
	}
	
	private static Collection<Point> getOutOfBoundsPoints(int sideLength)
	{
		ArrayList<Point> outOfBounds = new ArrayList<Point>();
		
		for(int row = 0; row < hexagonalHeightOffset(sideLength); row++)
		{
			for(int col = 0; col < hexagonalWidthOffset(sideLength); col++)
			{
				if(!isInHexagon(sideLength,row,col))
				{
					outOfBounds.add(new Point(row,col));
				}
			}
		}
		
		return outOfBounds;
	}
	
	private static boolean isInHexagon(int sideLength, int row, int col)
	{
		int displacement = (int)(Math.abs(row-(sideLength-1)));
		
		if(col > (displacement-1) && col < ((hexagonalWidthOffset(sideLength)-1)-(displacement-1)))
		{
			return true;
		}
		return false;
	}
	
}
