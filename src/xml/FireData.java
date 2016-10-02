package xml;

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

    public String getMyProbCatch () {
        return myProbCatch;
    }
    
    public String getMyInitialFire() {
        return myInitialFire;
    }


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
