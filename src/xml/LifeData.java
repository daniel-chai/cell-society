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
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append((super.getMyNumRows())).append("', ")
              .append("numCols='").append((super.getMyNumCols())).append("'")
              .append('}');
       return result.toString();
    }

}
