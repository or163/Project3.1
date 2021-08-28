package Remove;

import java.util.Optional;

import Model.Customer;
import Model.DeliveryPerson;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class RemoveDPController {

	@FXML
	private ListView<DeliveryPerson> dpLV;

	@FXML
	private Label message;
	
	@FXML
	private TextField id;

	// Initiate List View with all delivery persons inside
	public void initData() {

		dpLV.getItems().clear();
		for (DeliveryPerson dp : Main.restaurant.getDeliveryPersons().values()) {
			dpLV.getItems().add(dp);
		}
	}

	@FXML  // filter delivery person by id
	private void getDP(ActionEvent event) {
		if (!Utils.Utils.isOnlyDigits(id.getText()))
			return;
		DeliveryPerson dp = Main.restaurant.getRealDeliveryPerson(Integer.parseInt(id.getText()));
		dpLV.getSelectionModel().select(dp);
	}
	
	// this Method removes the selected delivery person from the restaurant
	@FXML
	private void remove(ActionEvent event) {
		DeliveryPerson dp = null;
		dp = dpLV.getSelectionModel().getSelectedItem();

		if (dpLV.getItems().size() == 0) //in case there are no delivery persons in the list
			message.setText("There are no delivery persons to remove");
		else if (dpLV.getSelectionModel().getSelectedItem() == null) //no delivery person selected
			message.setText("Please Select a delivery person to Remove");
		else if (dp != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION); //if returned ok from alert remove delivery person
			alert.setTitle("Confirmation");
			alert.setHeaderText(dpLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Main.restaurant.removeDeliveryPerson(dp);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");

			} // else { // user chose CANCEL or closed the dialog
		}

	}
}
