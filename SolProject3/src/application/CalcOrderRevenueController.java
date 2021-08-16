package application;

import Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CalcOrderRevenueController {

	@FXML
    private ListView<Order> orders;

    @FXML
    private TextField rev;

	public void initData() {
		// TODO Auto-generated method stub
		orders.getItems().addAll(Main.restaurant.getOrders().values());
	}
	
	@FXML
	private void calcOrderRevenue(ActionEvent event) {
		double revenue = orders.getSelectionModel().getSelectedItem().calcOrderRevenue();
		rev.setText(Double.toString(revenue));
	}
}
