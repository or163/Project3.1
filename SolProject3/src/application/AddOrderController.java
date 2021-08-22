package application;

import java.util.ArrayList;
import java.util.List;

import Exceptions.CantAddObjectException;
import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.Dish;
import Model.Order;
import Utils.DishType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class AddOrderController {

	@FXML
	private Label message;

	@FXML
	private ListView<Dish> selected;

	@FXML
	private ListView<Customer> custV;

	@FXML
	private ListView<Dish> dishV;

	//Initiate the page with current date and fill the combo-boxes with proper options
	public void initData() {
		// TODO Auto-generated method stub
		for (Customer c : Main.restaurant.getCustomers().values())
			custV.getItems().add(c);
		for (Dish d : Main.restaurant.getDishes().values())
			dishV.getItems().add(d);
	}

	@FXML   //save order to the restaurant
	void save(ActionEvent event) {
		Customer cust = custV.getSelectionModel().getSelectedItem();
		List<Dish> list = selected.getItems();
		ArrayList<Dish> dishes = new ArrayList<>(list);
		try {		//validates fields are not null
			if (cust == null || list == null) {
				message.setText("you have fields that are empty");
			} else {
				Order order = new Order(cust, dishes, null);
				if (Main.restaurant.addOrder(order)) { //if add succeeds than clear all fields for further adding
					message.setTextFill(Color.GREEN);
					message.setText("saved succesfully");
					custV.getSelectionModel().clearSelection();
					dishV.getSelectionModel().clearSelection();
					selected.getItems().clear();
				} else
					throw new CantAddObjectException("Order " + order.getId());
			}
		} catch (CantAddObjectException ex) {
			ex.alertMessage();
		}
	}

	@FXML  //add dish to selected dishes for order list view
	private void addDish(ActionEvent e) {
		if (dishV.getSelectionModel().getSelectedItem() != null)
			selected.getItems().add(dishV.getSelectionModel().getSelectedItem());
	}

	@FXML  //remove dish from selected dishes for order list view
	private void removeDish(ActionEvent e) {
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
	}

}
