package view;

import cellsociety_team07.Cell;
import cellsociety_team07.ColorMap;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import java.awt.Point;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import structure.Structure;;

/**
 * This class handles the graphical rendering of the Structure class. It has a pointer
 * to the Structure class because that way if a cell is added, it can be updated in the
 * View with out directly calling the View to update it.
 */
public abstract class StructureView implements Viewable
{
	private GridPane grid;
	private SoftReference<Structure> structure;
	private ColorMap colorMap;
	private Collection<CellView> cellViews;
	
	private int gridWidth;
	private int gridHeight;
	private int cellWidth;
	private int cellHeight;
	
	/**
	 * Creates a new Structure view of the desired width and height with a 
	 * SoftReference to its corresponding Structure
	 * @param width
	 * @param height
	 */
	public StructureView(Structure structure, ColorMap cm, int width, int height)
	{
		this.structure = new SoftReference<Structure>(structure);
		this.grid = new GridPane();
		this.cellViews = new ArrayList<CellView>();
		
		this.colorMap = cm;
		this.gridWidth = width;
		this.gridHeight = height;
		this.cellWidth = determineCellWidth();
		this.cellHeight = determineCellHeight();
		
		populateGridPane();
	}
	
	/**
	 * Convenience initilializer
	 * @param board
	 */
	public StructureView(Structure structure, ColorMap cm)
	{
		this(structure,cm,200,200);
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
		cellHeight = determineCellHeight();
	}
	
	/**
	 * Updates the Cell values in the GridPane
	 */
	public void populateGridPane()
	{
		for(Point point : structure.get().getAllPoints())
		{
			Cell current = structure.get().getCell(point);
			
			if(current != null && current.isValid())
			{
				formatAndAddCellView(current,point.x,point.y);
			}
		}
	} 
	
	/**
	 * Updates the Structure View by calling all the updateView() methods of the CellViews
	 */
	public void updateView()
	{
		for(CellView cv : cellViews)
		{
			cv.updateView();
		}
	}
	
	/**
	 * Creates a new CellView object for the Cell cell and places the node of the cell at (i,j) in the
	 * GridPane. Each CellView will have a width of width.
	 * @param cell - the model for the CellView
	 * @param width - desired width of the CellView
	 * @param i - i location of the CellView
	 * @param j - j location of the CellView
	 */
	private void formatAndAddCellView(Cell cell, int row, int col)
	{
		CellView cellView = getCellView(cell,row,col,colorMap);
		cellViews.add(cellView);
		cellView.setSize(cellWidth,cellHeight);
		grid.add(cellView.getNode(), col, row);
	}
	
	/**
	 * @return the desired width for cells
	 */
	private int determineCellWidth()
	{
		int maxCellHeight = gridHeight/structure.get().getHeight();
		return maxCellHeight;
	}
	
	/**
	 * @return the desired height for cells
	 */
	private int determineCellHeight()
	{
		int maxCellWidth = gridWidth/structure.get().getWidth();
		return maxCellWidth;
	}
	
	/**
	 * Defines the CellView for a given cell at location (row,col)
	 * @param cell- cell the view is representing
	 * @param row - the row location of that cell in the structure
	 * @param col - the column location of that cell in the structure
	 * @param cm - the ColorMap to define Color-State pairings
	 * @return
	 */
	abstract protected CellView getCellView(Cell cell,int row, int col,ColorMap cm);

}
