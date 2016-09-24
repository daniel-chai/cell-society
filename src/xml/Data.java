package xml;

/**
 * A value object for a Person.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public abstract class Data {
    private String myDataType;
    private String myTitle;
    private String myAuthor;
    private String myNumRows;
    private String myNumCols;
    private String myThreshold;
    
    
    public abstract String getMyDataType();

    public abstract String getMyTitle();

    public abstract String getMyAuthor();
    
    public abstract String toString();
}

