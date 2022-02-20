
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

import javafx.geometry.Insets;

//import javafx.beans.value.ObservableValue;


public class SimulationTab extends Tab {
	
	VBox vbox;
	Rectangle simulation;
	Insets insets;
	Pane simPane;
	
	public SimulationTab() {
		super("Simulation");
		vbox = new VBox(30);

		
		simulation = new Rectangle(500, 500);
		simulation.setFill(javafx.scene.paint.Color.rgb(220, 220, 220));
		simPane = new Pane();
		
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(simPane);
		
		this.setContent(vbox);
	}
	
	public void addPeopleToSimPane(Person[] people) {
		for (int i = 0; i < people.length; i++) {
			simPane.getChildren().add(people[i]);
		} //for
	} //addPeopleToSimPane
	
	public void updateFrame(Person[] people) {
		for (Person p : people) {
			p.setFill(Color.rgb(r(0, 255), r(0, 255), r(0, 255)));
		} //for
	} //updateFrame
		
	public static int r(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
}
