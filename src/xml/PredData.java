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
              .append("title='").append(super.getMyTitle()).append("', ")
              .append("author='").append((super.getMyAuthor())).append("', ")
              .append("numRows='").append(myNumRows).append("', ")
              .append("numCols='").append(myNumCols).append("', ")
              .append("fishBreed='").append((getMyFishBreed())).append("', ")
              .append("fishStarve='").append((getMyFishStarve())).append("', ")
              .append("sharkBreed='").append((getMySharkBreed())).append("', ")
              .append("sharkStarve='").append((getMySharkStarve())).append("'")
              .append('}');
        super.setDefaults(myNumRows, myNumCols);
       return result.toString();
    }
}
