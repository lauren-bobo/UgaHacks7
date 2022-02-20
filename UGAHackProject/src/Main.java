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
	VBox controlSliders;
	HBox startPop;
	HBox startNorm;
	HBox startInfect;
	
	Button startButton;	
	Separator vertSeparatorA;
	Label sliderLabel;
	Slider popSlider;
	Label sliderValue;
	Label typeLabel;
	Slider typeSlider;
	Label typeSliderValue;
	Label infectLabel;
	Slider infectSlider;
	Label infectSliderValue;
	
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
	
	/*
	 * Takes in Stage.
	 * @Param Stage
	 */
	public void start(Stage stage) {
		stage.setTitle("Tipping Point");
        stage.setScene(scene);
        stage.show();
	} //start
	
	/*
	 * Initializes the project. Creates the layout with
	 * The tabs, buttons, and controls. Contains the main scene.
	 */
	public void init() {
		helpMenu = new Menu("Help");
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(helpMenu);
		
		startButton = new Button("Start simulation");
		EventHandler<ActionEvent> startSimEvent = event -> {
			this.startSim();
		};
		startButton.setOnAction(startSimEvent);
		vertSeparatorA = new Separator(Orientation.VERTICAL);
		makeSliders();
		
		controlSliders = new VBox();
		controlSliders.getChildren().addAll(startPop, startNorm, startInfect);
		controlButtons = new HBox(10);
		controlButtons.getChildren().addAll(startButton, vertSeparatorA, controlSliders);
		
		tabs = new TabPane();
		simulation = new SimulationTab();
		graphs = new GraphTab();
		tabs.getTabs().addAll(simulation, graphs);
		
		//app vbox
		app = new VBox();
		app.getChildren().addAll(menuBar, controlButtons, tabs);
		
		scene = new Scene(app, 600, 600);

	} //init
	
	/*
	 * Creates the three simulation control sliders.
	 */
	public void makeSliders() {
		//population
		startPop = new HBox();
		sliderLabel = new Label("Starting population: ");
		popSlider = new Slider(20, 200, 50);
		popSlider.setSnapToTicks(true);
		popSlider.setShowTickMarks(true);
		popSlider.setMajorTickUnit(10.0);
		sliderValue = new Label();
		sliderValue.textProperty().bind(Bindings.format("%.2f", popSlider.valueProperty()));
		startPop.getChildren().addAll(sliderLabel, popSlider, sliderValue);
		
		//how many normal people
		startNorm = new HBox();
		typeLabel = new Label("% Starting Normal: ");
		typeSlider = new Slider(0, 100, 30);
		typeSlider.setSnapToTicks(true);
		typeSlider.setShowTickMarks(true);
		typeSlider.setMajorTickUnit(10.0);
		typeSliderValue = new Label();
		typeSliderValue.textProperty().bind(Bindings.format("%.2f", typeSlider.valueProperty()));
		startNorm.getChildren().addAll(typeLabel, typeSlider, typeSliderValue);
		
		//number of infected people starting off
		startInfect = new HBox();
		infectLabel = new Label("% Starting Infected: ");
		infectSlider = new Slider(0, 100, 20);
		infectSlider.setSnapToTicks(true);
		infectSlider.setShowTickMarks(true);
		infectSlider.setMajorTickUnit(10.0);
		infectSliderValue = new Label();
		infectSliderValue.textProperty().bind(Bindings.format("%.2f", infectSlider.valueProperty()));
		startInfect.getChildren().addAll(infectLabel, infectSlider, infectSliderValue);
	} //makeSliders
	
	/*
	 * Starts the simulation and starts the loop.
	 * Contains lambda with main simulation loop.
	 */
	public void startSim() {
		initializePeopleArray();
		//lambda simulation of one single loop
		KeyFrame updateFrame = new KeyFrame(fpsTarget, event -> {
			days++;
			spread();
			simulation.updateFrame(days, people);
			graphs.updateFrame(days, getNumHealthy(), getNumInfected());
		});
		loop.setCycleCount(Timeline.INDEFINITE);
		loop.getKeyFrames().add(updateFrame);
		simulation.updateFrame(days, people);
		graphs.updateFrame(days, getNumHealthy(), getNumInfected());
		loop.play(); //start the loop
	} //startSim
	
	/*
	 * Get the total population of the simulation.
	 * @return returns the total population
	 */
	public int getPopulation() {
		return (int)Double.parseDouble(sliderValue.getText());
	} //getPopulation
	
	/*
	 * Initializes the array of people for the simulation.
	 * Also gives each Person it's starting variables, type,
	 * and proximity (based on the persons around it)
	 */
	public void initializePeopleArray() {
		people = new Person[this.getPopulation()];
		app.getChildren().remove(1);
		for (int i = 0; i < people.length; i++) {
			people[i] = new Person(determineType());
			if (Person.getRandomNumber(0, 100) < (int)Double.parseDouble(infectSliderValue.getText())) {
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
	} //initializePeopleArray
	
	/*
	 * Determines someone's person type.
	 * Bases it off of Normal and everyone else. Normal is 
	 * controlled by a slider, the other three types are then
	 * given equal chances. This is all for a probability that someone
	 * becomes that type; it does not guarantee even distribution.
	 * @return PersonType returns a PersonType to be assigned
	 */
	public PersonType determineType() {
		int numNorm = (int)Double.parseDouble(sliderValue.getText());
		int compare = Person.getRandomNumber(0,  100);
		if (compare < numNorm) {
			return PersonType.valueOf("NORMAL");
		} //if
		int remainingPercent = (int)((100 - numNorm)/3);
		if (compare < numNorm + remainingPercent) {
			return PersonType.valueOf("CONNECTOR");
		} //if	
		if (compare < numNorm + (remainingPercent * 2)) {
			return PersonType.valueOf("SALESPERSON");
		} //if
		return PersonType.valueOf("MAVEN");
	} //determineType
	
	/*
	 * Returns the number of infected people out of the total
	 * @return int the number of infected people
	 */
	public int getNumInfected() {
		int numInfected = 0;
		for (Person person: people) {
			if(person.isInfected()) {
				numInfected++;
			} //if
		} //for
		return numInfected; 
	} //getNumInfected
	
	/*
	 * Returns the number of healthy people out of the total
	 * @return int the number of healthy people
	 */
	public int getNumHealthy() {
		int pop = getPopulation();
		return pop - getNumInfected(); 
	} //getNumHealthy
	
	/*
	 * Attempts to infect someone.
	 * @param Person the person trying to infect the other
	 * @param Person the person who is having an infection attempt on them
	 */
	public void attemptInfect(Person i, Person j) {
		double susceptible = Person.getRandomNumber(0,100);
		double contaminate = (i.getContagiousness() * 100) + (stickiness * 100);
		if (contaminate > susceptible) {
			j.setInfection(true);
		} //if	
	} //attemptInfect
	
	/*
	 * If a person is infected, it will check them with all of the people
	 * in their proximity for infection.
	 */
	public void spread() {
		for( int i = 0 ; i< people.length; i++) {
			if (people[i].isInfected()) {
				Person[] proximity = people[i].getProximity();
				for (int j = 0; j < proximity.length; j++) {
					if (!proximity[j].isInfected()) {
						attemptInfect(people[i], people[j]);
					} //if
				} //for
			} //if
		} //for
	} //spread
} //Main
