package xml;

import java.awt.Point;

public class FireData extends Data{
    private String myDataType;
    private String myTitle;
    private String myAuthor;
    private String myNumRows;
    private String myNumCols;
    private String myProbCatch;
    private String myInitialFire;
    
    public FireData (String title,
                 String author,
                 String numRows,
                 String numCols,
                 String probCatch, 
                 String initialFire) {
        
        myTitle = title;
        myAuthor = author;
        myNumRows = numRows;
        myNumCols = numCols;
        myProbCatch = probCatch;
        myInitialFire = initialFire;
    }
    
    @Override
    public String getMyDataType () {
        return myDataType;
    }

    @Override
    public String getMyTitle () {
        return myTitle;
    }
    
    @Override
    public String getMyAuthor () {
        return myAuthor;
    }

    public int getMyNumRows () {
        return Integer.parseInt(myNumRows);
    }

    public int getMyNumCols () {
        return Integer.parseInt(myNumCols);
    }

    public double getMyProbCatch () {
        return Double.parseDouble(myProbCatch);
    }
    
    public Point getMyInitialFire() {
		int index = myInitialFire.indexOf(',');
		
		int x = Integer.parseInt(myInitialFire.substring(0, index));
		int y = Integer.parseInt(myInitialFire.substring(index + 2));
		Point startCell = new Point(x, y);
		
        return startCell;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(getMyTitle()).append("', ")
              .append("author='").append((getMyAuthor())).append("', ")
              .append("numRows='").append((getMyNumRows())).append("', ")
              .append("numCols='").append((getMyNumCols())).append("', ")
              .append("probCatch='").append(getMyProbCatch()).append("', ")
              .append("initialFire='").append(getMyInitialFire()).append("'")
              .append('}');
       return result.toString();
    }
}
