import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;
import javafx.collections.ObservableList;

public class GraphTab extends Tab {
	VBox vbox;
	XYChart.Series infSeries;
	XYChart.Series heaSeries;
	public GraphTab() {
		super("Graph");
		this.setClosable(false);
		vbox = new VBox(30);
		
		final NumberAxis xAxis = new NumberAxis(0, 100, 10); 
        final NumberAxis yAxis = new NumberAxis(0, 200, 20); 
        xAxis.setLabel("Time");
        yAxis.setLabel("Infection %");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        
        infSeries = new XYChart.Series(); 
        infSeries.setName("Total people infected");
        heaSeries = new XYChart.Series(); 
        heaSeries.setName("Total people healthy");
        
        lineChart.getData().addAll(infSeries, heaSeries);

        lineChart.setCreateSymbols(false);
        lineChart.setTitle("Time vs Infection");
        vbox.getChildren().addAll(lineChart);
        
		this.setContent(vbox);
	} //GraphTab
	
	/*
	 * Updates every Frame
	 */
	public void updateFrame(int days, int healthy, int infected) {
		
		infSeries.getData().add(new XYChart.Data(days, infected));
		heaSeries.getData().add(new XYChart.Data(days, healthy));

	} //updateFrame
	
} //GraphTab
