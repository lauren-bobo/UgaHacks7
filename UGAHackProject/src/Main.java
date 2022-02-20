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

import java.io.*;
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
			people[i] = new Person();
		} //for
		simulation.addPeopleToSimPane(people);
		
		//lambda simulation of one single loop
		KeyFrame updateFrame = new KeyFrame(fpsTarget, event -> {
			simulation.updateFrame(people);
			graphs.updateFrame();
		});
		loop.setCycleCount(Timeline.INDEFINITE);
		loop.getKeyFrames().add(updateFrame);
		loop.play(); //start the loop
	} //startSim
	
	public int getPopulation() {
		return (int)Double.parseDouble(sliderValue.getText());
	} //getPopulation
	
	//print welcome to console - may not be needed
	private void printWelcome() {
		File welcomeFile = new File("resources/welcome.txt");
		Scanner file = null;
		
		try {
			file = new Scanner(welcomeFile);
			while (file.hasNextLine()) {
				System.out.println(file.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
} //Main
