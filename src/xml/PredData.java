package xml;

public class PredData extends Data{

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
        
        super.setMyTitle(title);
        super.setMyAuthor(author);
        super.setMyNumRows(numRows);
        super.setMyNumCols(numCols);
        myFishBreed = fishBreed;
        mySharkBreed = sharkBreed;
        myFishStarve = fishStarve;
        mySharkStarve = sharkStarve;
        
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
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append((super.getMyNumRows())).append("', ")
              .append("numCols='").append((super.getMyNumCols())).append("', ")
              .append("fishBreed='").append((getMyFishBreed())).append("', ")
              .append("fishStarve='").append((getMyFishStarve())).append("', ")
              .append("sharkBreed='").append((getMySharkBreed())).append("', ")
              .append("sharkStarve='").append((getMySharkStarve())).append("'")
              .append('}');
       return result.toString();
    }
}
