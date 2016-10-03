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
        else if(dataType.equals("slime")){
            return getSlimeMoldData(root);
        }
        else{
            checkDataType(root);
        }
        
        return null; 
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
        checkForErrors(root);
        return new PredData(title, author, numRows, numCols, fishBreed, sharkBreed, fishStarve, sharkStarve);
    }
    
    private FireData getFireData(Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        String probCatch = getTextValue(root, "probCatch");
        String initialFire = getTextValue(root, "initialFire");
        checkForErrors(root);
        return new FireData(title, author, numRows, numCols, probCatch, initialFire);
    }
    
    private LifeData getLifeData (Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        checkForErrors(root);
        return new LifeData(title, author, numRows, numCols);
    }

    private SegregationData getSegregationData (Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        String threshold = getTextValue(root, "threshold");
        checkForErrors(root);
        return new SegregationData(title, author, numRows, numCols, threshold);
    }
    
    private SlimeMoldData getSlimeMoldData (Element root) throws XMLFactoryException{
        String title = getTextValue(root, "title");
        String author = getTextValue(root, "author");
        String numRows = getTextValue(root, "numRows");
        String numCols = getTextValue(root, "numCols");
        String depositRate = getTextValue(root, "depositRate");
        String evaporationRate = getTextValue(root, "evaporationRate");
        checkForErrors(root);
        return new SlimeMoldData(title, author, numRows, numCols, depositRate, evaporationRate);
    }

    @Override
    protected boolean isValidFile (Element root) {
        return root.hasAttribute("dataType");
    }
    
    
    private void checkDataType(Element root){
        
        if(root.getAttribute("dataType").equals("")){
            System.out.println("No datatype given in file.");
        }
        else{
            System.out.println("Data type not supported.");
        }
    }
    
private void checkForErrors (Element root){
 
        try{
        Integer.parseInt(getTextValue(root, "numRows"));
        }catch (NumberFormatException e){
            throw new XMLParserException("Invalid entry in numRows");
        }
        
        try{
            Integer.parseInt(getTextValue(root, "numCols"));
        }catch (NumberFormatException e){ 
            throw new XMLParserException("Invalid entry in numCols");
        }
        
        if(root.getAttribute("dataType").equals("fire")){
            if(Double.parseDouble(getTextValue(root, "probCatch")) > 1 ||
                    Double.parseDouble(getTextValue(root, "probCatch")) < 0 ){
                throw new XMLParserException("Invalid entry in probCatch");
            }
        
        }
    }

}
