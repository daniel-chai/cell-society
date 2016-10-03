package xml;

import java.awt.Point;

public class FireData extends Data{

    private String myProbCatch;
    private String myInitialFire;
    
    public FireData (String title,
                 String author,
                 String numRows,
                 String numCols,
                 String probCatch, 
                 String initialFire) {
        
        super.setMyTitle(title);
        super.setMyAuthor(author);
        super.setMyNumRows(numRows);
        super.setMyNumCols(numCols);
        myProbCatch = probCatch;
        myInitialFire = initialFire;
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
    
    private void checkValidity() throws XMLParserException{
        if(getMyInitialFire().getX() > getMyNumCols() || 
                getMyInitialFire().getY() > getMyNumRows()){
            throw new XMLParserException("initalFire location not in grid boundaries");
        }
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append(myNumRows).append("', ")
              .append("numCols='").append(myNumCols).append("', ")
              .append("probCatch='").append(getMyProbCatch()).append("', ")
              .append("initialFire='").append(getMyInitialFire()).append("'")
              .append('}');
        checkValidity();
       return result.toString();
    }
}
