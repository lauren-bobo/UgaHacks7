/**
 * Main class for this project 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import java.io.*;
import java.util.*;
public class Main extends Application {
	
	Scene scene;
	VBox app;
	Menu helpMenu;
	Button button;
	MenuBar menuBar;
	TabPane tabs;
	SimulationTab simulation;
	GraphTab graphs;
	
	Person[] people;
	
	public void start(Stage stage) {
		//printWelcome();
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
			System.out.println(simulation.getPopulation());
		};
		button.setOnAction(startGameEvent);
		
		tabs = new TabPane();
		simulation = new SimulationTab();
		graphs = new GraphTab();
		tabs.getTabs().addAll(simulation, graphs);
		
		//app vbox
		app = new VBox();
		app.getChildren().addAll(menuBar, button, tabs);
		
		scene = new Scene(app, 600, 700);

	}
	
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
