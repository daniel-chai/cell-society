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

    public int getMyFishBreed () {
        return Integer.parseInt(myFishBreed);
    }

    public int getMySharkBreed () {
        return Integer.parseInt(mySharkBreed);
    }
    
    public int getMyFishStarve () {
        return Integer.parseInt(myFishStarve);
    }

    public int getMySharkStarve () {
        return Integer.parseInt(mySharkStarve);
    }

    @Override
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
