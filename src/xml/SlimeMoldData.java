package xml;

public class SlimeMoldData extends Data{

        private String myDepositRate;
        private String myEvaporationRate;
        
        public SlimeMoldData (String title,
                     String author,
                     String numRows,
                     String numCols,
                     String depositRate,
                     String evaporationRate) {
            
            super.setMyTitle(title);
            super.setMyAuthor(author);
            super.setMyNumRows(numRows);
            super.setMyNumCols(numCols);
            myDepositRate = depositRate;
            myEvaporationRate = evaporationRate;
        }

        public int getMyNumRows () {
            return Integer.parseInt(myNumRows);
        }

        public int getMyNumCols () {
            return Integer.parseInt(myNumCols);
        }

        public double getMyDepositRate () {
            return Double.parseDouble(myDepositRate);
        }
        
        public double getMyEvaporationRate () {
            return Double.parseDouble(myEvaporationRate);
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            result.append("Data{")
                  .append("title='").append(super.getMyTitle()).append("', ")
                  .append("author='").append((super.getMyAuthor())).append("', ")
                  .append("numRows='").append(myNumRows).append("', ")
                  .append("numCols='").append(myNumCols).append("', ")
                  .append("depositRate='").append(getMyDepositRate()).append("', ")
                  .append("evaporationRate='").append(getMyEvaporationRate()).append("'")
                  .append('}');
           return result.toString();
        }
    }
