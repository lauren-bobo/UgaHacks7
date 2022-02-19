/**
 * Main class for this project
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Main extends Application {
	
	Scene scene;
	VBox app;
	Menu helpMenu;
	MenuBar menuBar;
	TabPane tabs;
	Tab simulation;
	Tab graphs;
	
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
		simulation = new Tab("Simulation");
		graphs = new Tab("Graphs");
		tabs.getTabs().addAll(simulation, graphs);
		
		//app vbox
		app = new VBox();
		app.getChildren().addAll(menuBar, tabs);
		
		scene = new Scene(app, 1000, 1000);

	}
}
