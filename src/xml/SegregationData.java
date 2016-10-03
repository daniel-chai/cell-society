package xml;

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
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append(myNumRows).append("', ")
              .append("numCols='").append(myNumCols).append("', ")
              .append("threshold='").append(getMyThreshold()).append("'")
              .append('}');
       super.setDefaults(myNumRows, myNumCols);
       return result.toString();
    }
}
