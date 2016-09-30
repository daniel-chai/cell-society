package xml;

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
   
    public String getMyProbCatch () {
        return myProbCatch;
    }
    
    public String getMyInitialFire() {
        return myInitialFire;
    }

    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append((super.getMyNumRows())).append("', ")
              .append("numCols='").append((super.getMyNumCols())).append("', ")
              .append("probCatch='").append(getMyProbCatch()).append("', ")
              .append("initialFire='").append(getMyInitialFire()).append("'")
              .append('}');
       return result.toString();
    }
}
