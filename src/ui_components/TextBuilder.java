package ui_components;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class represents a common JavaFX Text component.
 * 
 * @author Daniel Chai
 */
public class TextBuilder {
	private Text text;
	
	private String textString = "";
	private double xLocation = 0;
	private double yLocation = 0;
	private String fontFamily = "Verdana";
	private int fontSize = 20;
	
	public TextBuilder() {
		text = new Text();
	}
	
	public Text build() {
		text.setText(textString);
		text.setX(xLocation);
		text.setY(yLocation);
		text.setFont(Font.font(fontFamily, fontSize));
		
		return text;
	}
	
	public TextBuilder setTextString(String textString) {
		this.textString = textString;
		return this;
	}
	
	public TextBuilder setXLocation(double xLocation) {
		this.xLocation = xLocation;
		return this;
	}
	
	public TextBuilder setYLocation(double yLocation) {
		this.yLocation = yLocation;
		return this;
	}
	
	public TextBuilder setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		return this;
	}
	
	public TextBuilder setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}
}
