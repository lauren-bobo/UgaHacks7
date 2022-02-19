
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.shape.Rectangle;

import javafx.scene.control.Separator;
import javafx.geometry.Orientation;

import javafx.scene.control.Button;

public class SimulationTab extends Tab {
	
	VBox vbox;
	HBox controlButtons;
	Button button;
	Separator vertSeparator;
	Rectangle simulation;
	
	public SimulationTab() {
		super("Simulation");
		vbox = new VBox(30);
		controlButtons = new HBox(10);
		button = new Button("Start simulation");
		vertSeparator = new Separator(Orientation.VERTICAL);
		
		simulation = new Rectangle(500, 500);
		simulation.setFill(javafx.scene.paint.Color.rgb(220, 220, 220));
		
		controlButtons.getChildren().addAll(button, vertSeparator);
		vbox.getChildren().addAll(controlButtons, simulation);
		
		this.setContent(vbox);
	}
}
