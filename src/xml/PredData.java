package xml;

public class PredData extends Data{

    private String myDataType;
    private String myTitle;
    private String myAuthor;
    private String myNumRows;
    private String myNumCols;
    private String myFishBreed;
    private String mySharkBreed;
    private String myFishStarve;
    private String mySharkStarve;
    
    public PredData (String title,
                 String author,
                 String numRows,
                 String numCols, 
                 String fishBreed,
                 String sharkBreed,
                 String fishStarve,
                 String sharkStarve){
        
        myTitle = title;
        myAuthor = author;
        myNumRows = numRows;
        myNumCols = numCols;
        myFishBreed = fishBreed;
        mySharkBreed = sharkBreed;
        myFishStarve = fishStarve;
        mySharkStarve = sharkStarve;
        
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


    public String getMyFishBreed () {
        return myFishBreed;
    }

    public String getMySharkBreed () {
        return mySharkBreed;
    }
    
    public String getMyFishStarve () {
        return myFishStarve;
    }

    public String getMySharkStarve () {
        return mySharkStarve;
    }

    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Data{")
              .append("title='").append(getMyTitle()).append("', ")
              .append("author='").append((getMyAuthor())).append("', ")
              .append("numRows='").append((getMyNumRows())).append("', ")
              .append("numCols='").append((getMyNumCols())).append("', ")
              .append("fishBreed='").append((getMyFishBreed())).append("', ")
              .append("fishStarve='").append((getMyFishStarve())).append("', ")
              .append("sharkBreed='").append((getMySharkBreed())).append("', ")
              .append("sharkStarve='").append((getMySharkStarve())).append("'")
              .append('}');
       return result.toString();
    }
}
