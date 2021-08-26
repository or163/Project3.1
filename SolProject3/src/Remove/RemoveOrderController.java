package Remove;

import java.util.Optional;

import Model.Order;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class RemoveOrderController {

	@FXML
	private ListView<Order> orderLV;

	@FXML
	private Label message;

	// Initiate List View with all orders inside
	public void initData() {

		orderLV.getItems().clear();
		for (Order o : Main.restaurant.getOrders().values()) {
			orderLV.getItems().add(o);
		}
	}

	// this Method removes the selected order from the restaurant
	@FXML
	private void remove(ActionEvent event) {
		Order o = null;
		o = orderLV.getSelectionModel().getSelectedItem();

		if (orderLV.getItems().size() == 0) //in case there are no orders in the list
			message.setText("There are no orders to remove");
		else if (orderLV.getSelectionModel().getSelectedItem() == null) //no order selected
			message.setText("Please Select a order to Remove");
		else if (o != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION); //if returned ok from alert remove order
			alert.setTitle("Confirmation");
			alert.setHeaderText(orderLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Main.restaurant.removeOrder(o);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");

			} // else { // user chose CANCEL or closed the dialog

		}

	}
}
