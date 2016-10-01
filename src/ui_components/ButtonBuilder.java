package ui_components;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * This class represents a common JavaFX Button component.
 */
public class ButtonBuilder {
	private Button button;
	
	private String text = "";
	private double xLocation = 0;
	private double yLocation = 0;
	private double width = 100;
	private double height = 25;
	private String fontFamily = "Verdana";
	private int fontSize = 20;
	
	public ButtonBuilder() {
		this.button = new Button();
	}
	
	public Button build() {
		button.setText(text);
		button.setLayoutX(xLocation);
		button.setLayoutY(yLocation);
		button.setMinWidth(width);
		button.setMinHeight(height);
		button.setFont(Font.font(fontFamily, fontSize));
		
		return button;
	}
	
	public ButtonBuilder setText(String text) {
		this.text = text;
		return this;
	}
	
	public ButtonBuilder setXLocation(double xLocation) {
		this.xLocation = xLocation;
		return this;
	}
	
	public ButtonBuilder setYLocation(double yLocation) {
		this.yLocation = yLocation;
		return this;
	}
	
	public ButtonBuilder setWidth(double width) {
		this.width = width;
		return this;
	}
	
	public ButtonBuilder setHeight(double height) {
		this.height = height;
		return this;
	}
	
	public ButtonBuilder setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		return this;
	}
	
	public ButtonBuilder setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}
}
