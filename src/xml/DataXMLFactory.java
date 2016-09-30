package xml;

import xml.Data;
import xml.XMLFactoryException;
import org.w3c.dom.Element;
import java.util.Objects;


/**
 * An XMLFactory that gives back a Person object.
 */
public class DataXMLFactory extends XMLFactory {

    public DataXMLFactory () {
    }

    public Data getData (Element root) throws XMLFactoryException{
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent valid data");
            }
        String dataType = getAttribute(root, "dataType");
        
        if(dataType.equals("segregation")){
            return getSegregationData(root);
        }
        else if(dataType.equals("gameoflife")){
            return getLifeData(root);
        }
        else if(dataType.equals("fire")){
            return getFireData(root);
        }
        else if(dataType.equals("predatorprey")){
            return getPredData(root);
        }

        else
        {
            checkError(dataType);
        }
        
        return null; 
    }
    
    private void checkError (String dataType) {
        if(dataType.equals("")){
            System.out.println("No datatype given in file.");
        }
        else{
            System.out.println("Data type not supported.");
        }
    }

    private PredData getPredData(Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        String fishBreed = getTextValue(root, "FishTurnsToBreed");
        String sharkBreed = getTextValue(root, "SharkTurnsToBreed");
        String fishStarve = getTextValue(root, "FishTurnsToStarve");
        String sharkStarve = getTextValue(root, "SharkTurnsToStarve");
        
        return new PredData(title, author, numRows, numCols, fishBreed, sharkBreed, fishStarve, sharkStarve);
    }
    
    private FireData getFireData(Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        String probCatch = getTextValue(root, "probCatch");
        String initialFire = getTextValue(root, "initialFire");
        
        
        return new FireData(title, author, numRows, numCols, probCatch, initialFire);
    }
    
    private LifeData getLifeData (Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        
        return new LifeData(title, author, numRows, numCols);
    }

    public SegregationData getSegregationData (Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        String threshold = getTextValue(root, "threshold");
        
        return new SegregationData(title, author, numRows, numCols, threshold);
    }

    @Override
    protected boolean isValidFile (Element root) {
        return true;
    }
}
