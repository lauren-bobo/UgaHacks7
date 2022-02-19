
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;

public class GraphTab extends Tab {
	VBox vbox;
	public GraphTab() {
		super("Graphs");
		vbox = new VBox();
		
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Infection%");

        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Chart");
        vbox.getChildren().addAll(lineChart);
        
		this.setContent(vbox);
		
	} //GraphTab
	
	
	
}
