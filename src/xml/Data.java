package xml;

/**
 * A value object for a Person.
 */
public abstract class Data {
    private String myDataType;
    private String myTitle;
    private String myAuthor;
    private String myNumRows;
    private String myNumCols;
    
    public String getMyDataType(){
        return myDataType;
    }

    public String getMyTitle(){
        return myTitle;
    }

    public String getMyAuthor(){
        return myAuthor;
    }

    public String getMyNumRows () {
        return myNumRows;
    }
    
    public String getMyNumCols() {
        return myNumCols;
    }
    
    public void setMyDataType (String myDataType) {
        this.myDataType = myDataType;
    }

    public void setMyTitle (String myTitle) {
        this.myTitle = myTitle;
    }

    public void setMyAuthor (String myAuthor) {
        this.myAuthor = myAuthor;
    }

    public void setMyNumRows (String myNumRows) {
        this.myNumRows = myNumRows;
    }

    public void setMyNumCols (String myNumCols) {
        this.myNumCols = myNumCols;
    }

    public abstract String toString();

}

