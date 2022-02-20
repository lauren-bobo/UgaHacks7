
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/*
 * SimulationTab extends Tab.
 */
public class SimulationTab extends Tab {
	
	VBox vbox;
	Insets insets;
	Pane simPane;
	HBox dayCounter;
	Label daysPassed;
	Label daysPassedNumber;
	Label text;
	
	/*
	 * Creates the simulation tab with the simulation box
	 * and other labels.
	 */
	public SimulationTab() {
		super("Simulation");
		this.setClosable(false);
		vbox = new VBox(30);
		simPane = new Pane();
		daysPassed = new Label("Days passed: ");
		daysPassedNumber = new Label("0");
		dayCounter = new HBox();
		dayCounter.setAlignment(Pos.TOP_CENTER);
		dayCounter.getChildren().setAll(daysPassed, daysPassedNumber);
		text = new Label();
		
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(dayCounter, simPane, text);
		
		this.setContent(vbox);
	} //SimulationTab
	
	/*
	 * Adds people to the simulation pane based on
	 * the people array. 
	 * @param people the array of people
	 */
	public void addPeopleToSimPane(Person[] people) {
		for (int i = 0; i < people.length; i++) {
			simPane.getChildren().add(people[i]);
		} //for
	} //addPeopleToSimPane
	
	/*
	 * Updates every frame
	 */
	public void updateFrame(int days, Person[] people) {
		daysPassedNumber.setText("" + days);
		for (Person p : people) {
			if (p.isInfected() ) {
				p.setFill(Color.RED);
			} else {
				p.setFill(Color.BLUE);
			} //if
		} //for
	} //updateFrame	
} //SimulationTab
