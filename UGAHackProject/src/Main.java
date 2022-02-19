/**
 * Main class for this project 
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

import javafx.scene.control.Label;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import java.io.*;
import java.util.*;
public class Main extends Application {
	
	Scene scene;
	VBox app;
	Menu helpMenu;
	MenuBar menuBar;
	TabPane tabs;
	SimulationTab simulation;
	GraphTab graphs;
	
	Person[] people = new Person[100];
	
	public void start(Stage stage) {
		
		stage.setTitle("Tipping Point");
        stage.setScene(scene);
        stage.show();
	
	}
	
	public void init() {
		helpMenu = new Menu("Help");
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(helpMenu);
		
		tabs = new TabPane();
		simulation = new SimulationTab();
		graphs = new GraphTab();
		tabs.getTabs().addAll(simulation, graphs);
		
		//app vbox
		app = new VBox();
		app.getChildren().addAll(menuBar, tabs);
		
		scene = new Scene(app, 1000, 1000);

	}
	
	public void printWelcome() {
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
