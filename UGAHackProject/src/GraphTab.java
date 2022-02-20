import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;
import javafx.collections.ObservableList;

public class GraphTab extends Tab {
	VBox vbox;
	public GraphTab() {
		super("Graph");
		this.setClosable(false);
		vbox = new VBox(30);
		
		final NumberAxis xAxis = new NumberAxis(); //i
        final NumberAxis yAxis = new NumberAxis(); //total susceptable
        xAxis.setLabel("Time");
        yAxis.setLabel("Infection %");

        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Time vs Infection Percent");
        vbox.getChildren().addAll(lineChart);
        
		this.setContent(vbox);
	} //GraphTab
	
	/*
	 * Updates every Frame
	 */
	public void updateFrame() {
		
	} //updateFrame
	
} //GraphTab
