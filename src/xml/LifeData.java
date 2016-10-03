package xml;

public class LifeData extends Data{

    public LifeData (String title,
                 String author,
                 String numRows,
                 String numCols) {
        
    	super.setMyTitle(title);
        super.setMyAuthor(author);
        super.setMyNumRows(numRows);
        super.setMyNumCols(numCols);
    }
    
    public int getMyNumRows () {
        return Integer.parseInt(myNumRows);
    }

    public int getMyNumCols () {
        return Integer.parseInt(myNumCols);
    }
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append(myNumRows).append("', ")
              .append("numCols='").append(myNumCols).append("'")
              .append('}');
        super.setDefaults(myNumRows, myNumCols);
       return result.toString();
    }

}
