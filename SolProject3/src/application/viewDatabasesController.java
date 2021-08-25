package application;

import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class viewDatabasesController {

	@FXML
	private ComboBox<String> ChosenData;

	@FXML
	private ListView<Object> LVdb;

	@FXML
	private TextField id;

	public void initData() {
		// TODO Auto-generated method stub
		ChosenData.getItems().addAll("cooks", "deliveryPersons", "customers", "dishes", "componenets", "orders",
				"deliveries", "areas", "orderByCustomer", "orderByDeliveryArea", "blackList");
	}

	@FXML
	void GetData(ActionEvent event) {
		String chosen = ChosenData.getSelectionModel().getSelectedItem();
		LVdb.getItems().clear();
		switch (chosen) {
		case "cooks":
			LVdb.getItems().addAll(Main.restaurant.getCooks().values());
			break;
		case "deliveryPersons":
			LVdb.getItems().addAll(Main.restaurant.getDeliveryPersons().values());
			break;
		case "customers":
			LVdb.getItems().addAll(Main.restaurant.getCustomers().values());
			break;
		case "dishes":
			LVdb.getItems().addAll(Main.restaurant.getDishes().values());
			break;
		case "componenets":
			LVdb.getItems().addAll(Main.restaurant.getComponenets().values());
			break;
		case "orders":
			LVdb.getItems().addAll(Main.restaurant.getOrders().values());
			break;
		case "deliveries":
			LVdb.getItems().addAll(Main.restaurant.getDeliveries().values());
			break;
		case "areas":
			LVdb.getItems().addAll(Main.restaurant.getAreas().values());
			break;
		case "orderByCustomer":
			LVdb.getItems().addAll(Main.restaurant.getOrderByCustomer().values());
			break;
		case "orderByDeliveryArea":
			LVdb.getItems().addAll(Main.restaurant.getOrderByDeliveryArea().values());
			break;
		case "blackList":
			LVdb.getItems().addAll(Main.restaurant.getBlackList());
			break;
		}
	}

	@FXML
	private void getObject(ActionEvent event) {
		if (!Utils.Utils.isOnlyDigits(id.getText()))
			return;
		String chosen = ChosenData.getSelectionModel().getSelectedItem();
		switch (chosen) {
		case "cooks":
			Cook cook = Main.restaurant.getRealCook(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(cook);
			break;
		case "deliveryPersons":
			DeliveryPerson dp = Main.restaurant.getRealDeliveryPerson(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(dp);
			break;
		case "customers":
			Customer cust = Main.restaurant.getRealCustomer(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(cust);
			break;
		case "dishes":
			Dish dish = Main.restaurant.getRealDish(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(dish);
			break;
		case "componenets":
			Component comp = Main.restaurant.getRealComponent(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(comp);
			break;
		case "orders":
			Order order = Main.restaurant.getRealOrder(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(order);
			break;
		case "deliveries":
			Delivery del = Main.restaurant.getRealDelivery(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(del);
			break;
		case "areas":
			DeliveryArea da = Main.restaurant.getRealDeliveryArea(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(da);
			break;
		}
	}

}
