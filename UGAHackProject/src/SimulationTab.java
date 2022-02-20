
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.shape.Rectangle;

import javafx.scene.control.Separator;
import javafx.geometry.Orientation;

import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.geometry.Insets;


import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SimulationTab extends Tab {
	
	VBox vbox;
	HBox controlButtons;
	Separator vertSeparator;
	Label sliderLabel;
	Slider slider;
	Label sliderValue;
	Rectangle simulation;
	Insets insets;
	
	public SimulationTab() {
		super("Simulation");
		vbox = new VBox(30);
		controlButtons = new HBox(10);
		
		
		sliderLabel = new Label("Starting population: ");
		slider = new Slider(20, 200, 50);
		slider.setSnapToTicks(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10.0);
		sliderValue = new Label();
		sliderValue.textProperty().bind(Bindings.format("%.2f", slider.valueProperty()));
		
		vertSeparator = new Separator(Orientation.VERTICAL);
		
		simulation = new Rectangle(500, 500);
		simulation.setFill(javafx.scene.paint.Color.rgb(220, 220, 220));
		
		controlButtons.getChildren().addAll(sliderLabel, slider, sliderValue, vertSeparator);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(controlButtons, simulation);
		
		this.setContent(vbox);
	}
	

	
	public int getPopulation() {
		return (int)Double.parseDouble(sliderValue.getText());
	} //getPopulation
}
