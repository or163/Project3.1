package application;

import java.util.LinkedList;

import Model.Component;
import Model.DeliveryArea;
import Model.Order;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OrderWaitingTimeController {

    @FXML
    private BarChart<String, Integer> timeChart;

    @FXML
    private ComboBox<Order> orders;

    @FXML
    private ComboBox<DeliveryArea> da;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    @FXML
    private TextField time;

    public void initData() {
    	orders.getItems().addAll(Main.restaurant.getOrders().values());
    	da.getItems().addAll(Main.restaurant.getAreas().values());
    }
    
    @FXML
    private void addGraph() {
    	Order o = orders.getSelectionModel().getSelectedItem();
    	DeliveryArea area = da.getSelectionModel().getSelectedItem();
    	int waitingTime = o.orderWaitingTime(area);
    	Series<String, Integer> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<>("", waitingTime));
		series.setName("Order: " + Integer.toString(o.getId()) + "  Area: " + area.getAreaName());
		timeChart.getData().add(series);
		time.setText(Integer.toString(waitingTime));
    }
    
    @FXML
    private void clearGraph() {
    	timeChart.getData().clear();
    }
    
}
