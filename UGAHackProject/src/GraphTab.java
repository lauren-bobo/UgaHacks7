
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;

public class GraphTab extends Tab {
	VBox vbox;
	public GraphTab() {
		super("Time vs Infection");
		vbox = new VBox(30);
		
		final NumberAxis xAxis = new NumberAxis(); //i
        final NumberAxis yAxis = new NumberAxis(); //total susceptable
        xAxis.setLabel("Time");
        yAxis.setLabel("Infection %");

        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Chart");
        vbox.getChildren().addAll(lineChart);
        
		this.setContent(vbox);
		
	} //GraphTab
	
	
	
}
