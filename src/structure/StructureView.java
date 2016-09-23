package structure;

import cellsociety_team07.Cell;
import cellsociety_team07.CellView;
import cellsociety_team07.Viewable;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public class StructureView implements Viewable
{
	private GridPane grid;
	private Cell[][] board;
	private int gridWidth;
	private int gridHeight;
	private int cellWidth;
	
	/**
	 * Creates a new Structure view of the desired width and height
	 * @param width
	 * @param height
	 */
	public StructureView(Cell[][] board, int width, int height)
	{
		this.board = board;
		grid = new GridPane();
		gridWidth = width;
		gridHeight = height;
		cellWidth = determineCellWidth();
		
		populateGridPane();
	}
	
	/**
	 * Convenience initilializer
	 * @param board
	 */
	public StructureView(Cell[][] board)
	{
		this(board,100,100);
	}
	/**
	 * Returns the GridPane that contains the GUI of the board
	 */
	public Node getNode()
	{
		populateGridPane();
		return grid;
	}
	
	/**
	 * Sets the size of the GridPane to width by height
	 * @param width- the desired width
	 * @param height- the desired height
	 */
	public void setSizeOfView(int width, int height)
	{
		gridWidth = width;
		gridHeight = height;
		cellWidth = determineCellWidth();
	}
	
	/**
	 * Updates the Cell values in the GridPane
	 */
	public void populateGridPane()
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				Cell current = board[i][j];
				if(isValidNode(current))
				{
					formatAndAddCellView(current,cellWidth,i,j);
				}
			}
		}
	}
	
	private boolean isValidNode(Cell cell)
	{
		return cell != null && !cell.equals(Cell.OUT_OF_BOUNDS);
	}
	
	private void formatAndAddCellView(Cell c, int width, int i, int j)
	{
		CellView cellView = c.getView();
		cellView.setWidth(width);
		grid.add(cellView.getNode(), i, j);
	}
	
	private int determineCellWidth()
	{
		int maxCellHeight = gridWidth/board[0].length;
		int maxCellWidth = gridHeight/board.length;
		return Math.min(maxCellHeight, maxCellWidth);
	}
	
	
	

}
