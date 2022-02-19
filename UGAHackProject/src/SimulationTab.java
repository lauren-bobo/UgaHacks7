import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.control.Button;

public class SimulationTab extends Tab {
	
	VBox vbox;
	HBox controlButtons;
	Button button;
	
	public SimulationTab() {
		super("Simulation");
		vbox = new VBox();
		controlButtons = new HBox();
		button = new Button("Start simulation");
		
		controlButtons.getChildren().addAll(button);
		vbox.getChildren().addAll(controlButtons);
		
		this.setContent(vbox);
	}
}
