
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.shape.Rectangle;

import javafx.geometry.Insets;


import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SimulationTab extends Tab {
	
	VBox vbox;
	Rectangle simulation;
	Insets insets;
	
	public SimulationTab() {
		super("Simulation");
		vbox = new VBox(30);
		
		
		
		
		
		
		simulation = new Rectangle(500, 500);
		simulation.setFill(javafx.scene.paint.Color.rgb(220, 220, 220));
		
		
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(simulation);
		
		this.setContent(vbox);
	}
	

	
	
}
