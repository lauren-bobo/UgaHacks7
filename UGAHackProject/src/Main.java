/**
 * Main class for this project 
 */
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

import javafx.scene.Group;
import javafx.scene.shape.Circle;
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
	
	Person[] people;
	
	public void start(Stage stage) {
		stage.setTitle("Tipping Point");
        stage.setScene(scene);
        stage.show();
	
	}
	
	public void init() {
		helpMenu = new Menu("Help");
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(helpMenu);
		
		button = new Button("Start simulation");
		EventHandler<ActionEvent> startGameEvent = event -> {
			people = new Person[this.getPopulation()];
			app.getChildren().remove(1);
		};
		button.setOnAction(startGameEvent);
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
		
		scene = new Scene(app, 600, 700);

	}
	
	public int getPopulation() {
		return (int)Double.parseDouble(sliderValue.getText());
	} //getPopulation
	
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
}
