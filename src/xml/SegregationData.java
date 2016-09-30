package xml;

/**
 * A value object for a Person.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class SegregationData extends Data{

    private String myThreshold;
    
    public SegregationData (String title,
                 String author,
                 String numRows,
                 String numCols,
                 String threshold) {
        
        super.setMyTitle(title);
        super.setMyAuthor(author);
        super.setMyNumRows(numRows);
        super.setMyNumCols(numCols);
        myThreshold = threshold;
    }
   
    public String getMyThreshold () {
        return myThreshold;
    }


    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append((super.getMyNumRows())).append("', ")
              .append("numCols='").append((super.getMyNumCols())).append("', ")
              .append("threshold='").append(getMyThreshold()).append("'")
              .append('}');
       return result.toString();
    }
}
