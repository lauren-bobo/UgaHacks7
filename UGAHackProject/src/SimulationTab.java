
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
		this.setClosable(false);
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
			//p.setFill(Color.rgb(r(0, 255), r(0, 255), r(0, 255)));
			if (p.isInfected() ) {
				p.setFill(Color.RED);
			} else {
				p.setFill(Color.BLUE);
			} //if
			/*
			for (Person p2 : people[5].getProximity()) {
				p2.setFill(Color.GREEN);
			} //for
			*/
		} //for

	} //updateFrame
		
	//random number - not necessary later. can delete
	public static int r(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
	
	public String getHeadLine(double x) {
		Scanner input = null;
		
		int y;
		
		if (x >= 0 || x <= 0.05) 
			y = 1;
		else if (x >= 0.05 || x <= 0.1)
			y = 2;
		else if (x >= 0.1 || x <= 0.15)
			y = 3;
		else if (x >= 0.15 || x <= 0.2)
			y = 4;
		else if (x >= 0.2 || x <= 0.25)
			y = 5;
		else if (x >= 0.25 || x <= 0.3)
			y = 6;
		else if (x >= 0.3 || x <= 0.35)
			y = 7;
		else if (x >= 0.35 || x <= 0.4)
			y = 8;
		else if (x >= 0.4 || x <= 0.45)
			y = 9;
		else if (x >= 0.45 || x <= 0.5)
			y = 10;
		else
			return "Number Invalid.";
		
		try {
			File headlineFile = new File(UGAHackProject/resources/"headline" + y + ".txt");
			input = new Scanner(headlineFile);
			int z;
			if (y <= 5) 
				z = 7;
			else
				z = 3;
			int dest = (int) ((Math.random() * (1 - z)) + 1);
			String out = "";
			for (int i = 0; i <= dest; i++) {
				out = input.nextLine();
			}
			return out;
		} catch (FileNotFoundException e) {
			return "File Not Found";
		} catch (NoSuchElementException e) {
			return "Element Not Found";
		}
		
	} //getHeadLine
	
} //SimulationTab
