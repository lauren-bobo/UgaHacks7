
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
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
	Label headline;
	Region spacer;
	int headlineTick;
	StringReference reference = new StringReference();
	
	/*
	 * Creates the simulation tab with the simulation box
	 * and other labels.
	 */
	public SimulationTab() {
		super("Simulation");
		this.setClosable(false);
		vbox = new VBox(30);
		simPane = new Pane();
		simPane.setPrefSize(500, 360);
		daysPassed = new Label("Days passed: ");
		daysPassedNumber = new Label("0");
		dayCounter = new HBox();
		dayCounter.setAlignment(Pos.TOP_CENTER);
		dayCounter.getChildren().setAll(daysPassed, daysPassedNumber);
		spacer = new Region();
		spacer.setPrefHeight(200);
		headline = new Label(getRandomHeadline());
		headline.setMaxWidth(500);		
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(dayCounter, simPane, spacer, headline);
		
		this.setContent(vbox);
	} //SimulationTab
	
	/*
	 * Returns a random, wacky headline from the 
	 * String Reference Class.
	 * @return String of the wacky headline.
	 */
	public String getRandomHeadline() {
		int wackScaleX = Person.getRandomNumber(0, 11);
		int wackScaleYRand;
		if (wackScaleX > 5) {
			wackScaleYRand = 3;
		} else {
			wackScaleYRand = 7;
		} //if
		int wackScaleY = Person.getRandomNumber(0, wackScaleYRand);
		return reference.getDescription(wackScaleX, wackScaleY);
	} //getRandomHeadline
	
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
	 * Updates every frame. Changes color based on type and infection status.
	 */
	public void updateFrame(int days, Person[] people) {
		headlineTick++;
		daysPassedNumber.setText("" + days);
		for (Person p : people) {
			Color c;
			switch (p.getPersonType()) {
				case SALESPERSON:
					c = Color.rgb(246, 239, 36);
					break;
				case MAVEN:
					c = Color.rgb(212, 0, 64);
					break;
				case CONNECTOR:
					c = Color.rgb(242, 100, 83);
					break;
				default:
					c = Color.rgb(143, 143, 158);
			} //switch
			if (p.isInfected()) {
				p.setFill(c.darker().darker());
			} else {
				p.setFill(c);
			} //if
		} //for
		if (headlineTick % 15 == 0) {
			headline.setText(getRandomHeadline());
		} //if
	} //updateFrame	
} //SimulationTab
