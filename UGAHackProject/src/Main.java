import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;

import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.*;
public class Main extends Application {
	
	Scene scene;
	VBox app;
	Menu helpMenu;
	
	HBox controlButtons;
	Button button;	
	Separator vertSeparator;
	Label sliderLabel;
	Slider slider;
	Label sliderValue;
	
	MenuBar menuBar;
	TabPane tabs;
	SimulationTab simulation;
	GraphTab graphs;
	
	double stickiness;
	int days;
	
	private static final double FPS = 1; //this is the fps the sim will run at
	private final Duration fpsTarget = Duration.millis(1000.0 / FPS); 
	private final Timeline loop = new Timeline();
	
	Person[] people;
	
	public void start(Stage stage) {
		stage.setTitle("Tipping Point");
        stage.setScene(scene);
        stage.show();
	} //start
	
	public void init() {
		helpMenu = new Menu("Help");
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(helpMenu);
		
		button = new Button("Start simulation");
		EventHandler<ActionEvent> startSimEvent = event -> {
			this.startSim();
		};
		button.setOnAction(startSimEvent);
		
		vertSeparator = new Separator(Orientation.VERTICAL);
		sliderLabel = new Label("Starting population: ");
		slider = new Slider(20, 200, 50);
		slider.setSnapToTicks(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10.0);
		sliderValue = new Label();
		sliderValue.textProperty().bind(Bindings.format("%.2f", slider.valueProperty()));
		controlButtons = new HBox(10);
		controlButtons.getChildren().addAll(button, vertSeparator, sliderLabel, slider, sliderValue);
		
		tabs = new TabPane();
		simulation = new SimulationTab();
		graphs = new GraphTab();
		tabs.getTabs().addAll(simulation, graphs);
		
		//app vbox
		app = new VBox();
		app.getChildren().addAll(menuBar, controlButtons, tabs);
		
		scene = new Scene(app, 600, 600);

	} //init
	
	public void startSim() {
		people = new Person[this.getPopulation()];
		app.getChildren().remove(1);
		for (int i = 0; i < people.length; i++) {
			people[i] = new Person(PersonType.valueOf("CONNECTOR"));
			if (Person.getRandomNumber(0, 100) > 80) {
				people[i].setInfection(true);
			} //if
		} //for
		for (int i = 0; i < people.length; i++) {
			ArrayList<Person> temp = new ArrayList<>();
			for (int j = 0; j < people.length; j++) {
				if (i != j && people[i].distanceTo(people[j]) <= 
						people[i].getSphereOfInfluence()) {
					temp.add(people[j]);
				} //if
			} //for
			Person[] proximity = new Person[temp.size()];
			for (int k = 0; k < proximity.length; k++) {
				proximity[k] = temp.get(k);
			} //for
			people[i].setProximity(proximity);
		} //for
		simulation.addPeopleToSimPane(people);
		
		//lambda simulation of one single loop
		KeyFrame updateFrame = new KeyFrame(fpsTarget, event -> {
			days++;
			spread(people);
			simulation.updateFrame(people);
			graphs.updateFrame(days, getNumHealthy(), getNumInfected());
		});
		loop.setCycleCount(Timeline.INDEFINITE);
		loop.getKeyFrames().add(updateFrame);
		simulation.updateFrame(people);
		graphs.updateFrame(days, getNumHealthy(), getNumInfected());
		loop.play(); //start the loop
	} //startSim
	
	public int getPopulation() {
		return (int)Double.parseDouble(sliderValue.getText());
	} //getPopulation
	
	/* */
	/* */
	
	public int getNumInfected() {
		int numInfected = 0;
		for (Person person: people) {
			if(person.isInfected()) {
				numInfected++;
			} //if
		} //for
		return numInfected; 
	} //getNumInfected
	
	public int getNumHealthy() {
		int pop = getPopulation();
		return pop - getNumInfected(); 
	} //getNumHealthy
	
	public void attemptInfect(Person i, Person j) {
		double susceptible = Person.getRandomNumber(0,100);
		double contaminate = (i.getContagiousness() * 100) + (stickiness * 100);
		if (contaminate > susceptible) {
			j.setInfection(true);
		} //if	
	} //attemptInfect
	
	public void spread(Person[] population) {
		for( int i = 0 ; i< population.length; i++) {
			if (population[i].isInfected()) {
				Person[] proximity = population[i].getProximity();
				for (int j = 0; j < proximity.length; j++) {
					if (!proximity[j].isInfected()) {
						attemptInfect(population[i], population[j]);
					} //if
				} //for
			} //if
		} //for
	} //spread
	
	
} //Main
