package xml;

public class LifeData extends Data{
    private String myDataType;
    private String myTitle;
    private String myAuthor;
    private String myNumRows;
    private String myNumCols;
   
    public LifeData (String title,
                 String author,
                 String numRows,
                 String numCols) {
        
        myTitle = title;
        myAuthor = author;
        myNumRows = numRows;
        myNumCols = numCols;
    }

    @Override
    public String getMyDataType () {
        // TODO Auto-generated method stub
        return myDataType;
    }

    @Override
    public String getMyTitle () {
        // TODO Auto-generated method stub
        return myTitle;
    }

    @Override
    public String getMyAuthor () {
        // TODO Auto-generated method stub
        return myAuthor;
    }
    
    public String getMyNumRows () {
        return myNumRows;
    }

    public String getMyNumCols () {
        return myNumCols;
    }
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(getMyTitle()).append("', ")
              .append("author='").append((getMyAuthor())).append("', ")
              .append("numRows='").append((getMyNumRows())).append("', ")
              .append("numCols='").append((getMyNumCols())).append("'")
              .append('}');
       return result.toString();
    }

}
