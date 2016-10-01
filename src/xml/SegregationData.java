package xml;

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

    public double getMyThreshold () {
        return Double.parseDouble(myThreshold);
    }

    @Override
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
