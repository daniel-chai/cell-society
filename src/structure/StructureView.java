package structure;

import cellsociety_team07.Cell;
import cellsociety_team07.CellView;
import cellsociety_team07.Viewable;
import javafx.scene.layout.GridPane;
import java.lang.ref.SoftReference;
import javafx.scene.Node;

public class StructureView implements Viewable
{
	private GridPane grid;
	private Cell[][] board;
	private final int WIDTH;
	private final int HEIGHT;
	
	/**
	 * Creates a new Structure view of the desired width and height
	 * @param width
	 * @param height
	 */
	public StructureView(Cell[][] board, int width, int height)
	{
		this.board = board;
		grid = new GridPane();
		WIDTH = width;
		HEIGHT = height;
		
		populateGridPane();
	}
	
	/**
	 * Returns the GridPane that contains the GUI of the board
	 */
	public Node getNode()
	{
		return grid;
	}
	
	private void populateGridPane()
	{
		int cellWidth = determineCellWidth();

		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				Cell current = board[i][j];
				if(current != null && !current.equals(Cell.OUT_OF_BOUNDS))
				{
					formatAndAddCellView(current,cellWidth,i,j);
				}
			}
		}
	}
	
	private void formatAndAddCellView(Cell c, int width, int i, int j)
	{
		CellView cellView = c.getView();
		cellView.setWidth(width);
		grid.add(cellView.getNode(),i,j);
	}
	
	private int determineCellWidth()
	{
		int maxCellHeight = WIDTH/board[0].length;
		int maxCellWidth = HEIGHT/board.length;
		return Math.min(maxCellHeight, maxCellWidth);
	}
	
	
	

}
