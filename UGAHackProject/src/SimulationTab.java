
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import javafx.geometry.Insets;

//import javafx.beans.value.ObservableValue;


public class SimulationTab extends Tab {
	
	VBox vbox;
	Insets insets;
	Pane simPane;
	
	public SimulationTab() {
		super("Simulation");
		vbox = new VBox(30);
		simPane = new Pane();
		
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(simPane);
		
		this.setContent(vbox);
	} //SimulationTab
	
	public void addPeopleToSimPane(Person[] people) {
		for (int i = 0; i < people.length; i++) {
			simPane.getChildren().add(people[i]);
		} //for
	} //addPeopleToSimPane
	
	/*
	 * Updates every frame
	 */
	public void updateFrame(Person[] people) {
		for (Person p : people) {
			p.setFill(Color.rgb(r(0, 255), r(0, 255), r(0, 255)));
		} //for
	} //updateFrame
		
	//random number - not necessary later. can delete
	public static int r(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
} //SimulationTab
