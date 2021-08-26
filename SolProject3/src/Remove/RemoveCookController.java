package Remove;

import java.util.Optional;

import Model.Cook;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RemoveCookController {

	@FXML
	private ListView<Cook> cookLV;

	@FXML
	private Label message;

	// Initiate List View with all cooks inside
	public void initData() {

		cookLV.getItems().clear();
		for (Cook c : Main.restaurant.getCooks().values()) {
			cookLV.getItems().add(c);
		}
	}

	// this Method removes the selected cook from the restaurant
	@FXML
	private void remove(ActionEvent event) {
		Cook c = null;
		c = cookLV.getSelectionModel().getSelectedItem();

		if (cookLV.getItems().size() == 0) //in case there are no cooks in the list
			message.setText("There are no cooks to remove");
		else if (cookLV.getSelectionModel().getSelectedItem() == null) //no cook selected
			message.setText("Please Select a bird to Remove");
		else if (c != null) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION); //if returned ok from alert remove cook
			alert.setTitle("Confirmation");
			alert.setHeaderText(cookLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Main.restaurant.removeCook(c);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");

			} // else { // user chose CANCEL or closed the dialog
		}

	}

	@FXML
	void DeleteMessage(MouseEvent event) {
		message.setText(null);
	}

}
