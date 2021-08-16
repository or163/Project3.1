package application;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TextField;

public class RevenueFromExpressDeliveriesController {

	@FXML
    private BarChart<String, Double> chart;

    @FXML
    private TextField rev;

	@FXML
    private void addGraph() {
		chart.getData().clear();
    	double revenue = Main.restaurant.revenueFromExpressDeliveries();
    	Series<String, Double> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<>("", revenue));
		series.setName("Revenue");
		chart.getData().add(series);
		rev.setText(Double.toString(revenue));
    }
}
