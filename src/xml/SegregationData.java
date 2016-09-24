package xml;

/**
 * A value object for a Person.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class SegregationData extends Data{
    private String myDataType;
    private String myTitle;
    private String myAuthor;
    private String myNumRows;
    private String myNumCols;
    private String myThreshold;
    
    public SegregationData (String title,
                 String author,
                 String numRows,
                 String numCols,
                 String threshold) {
        
        myTitle = title;
        myAuthor = author;
        myNumRows = numRows;
        myNumCols = numCols;
        myThreshold = threshold;
    }
    

    public String getMyDataType () {
        return myDataType;
    }

    public String getMyTitle () {
        return myTitle;
    }

    public String getMyAuthor () {
        return myAuthor;
    }

    public String getMyNumRows () {
        return myNumRows;
    }

    public String getMyNumCols () {
        return myNumCols;
    }

    public String getMyThreshold () {
        return myThreshold;
    }


    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(getMyTitle()).append("', ")
              .append("author='").append((getMyAuthor())).append("', ")
              .append("numRows='").append((getMyNumRows())).append("', ")
              .append("numCols='").append((getMyNumCols())).append("', ")
              .append("threshold='").append(getMyThreshold()).append("'")
              .append('}');
       return result.toString();
    }
}
