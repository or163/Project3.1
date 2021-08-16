package Remove;

import java.util.Optional;

import Model.Customer;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class RemoveCustomerController {

    @FXML
    private ListView<Customer> customerLV;

    @FXML
    private Label message;

    public void initData(){

		customerLV.getItems().clear();
		for (Customer c : Main.restaurant.getCustomers().values()) {
			customerLV.getItems().add(c);
		}
	}


	//this Method Initialize the input fields of the class//
	@FXML
	private void remove(ActionEvent event) {
		Customer c = null;
		c = customerLV.getSelectionModel().getSelectedItem();
		
		if(customerLV==null)
			message.setText("There are no customers to remove");
		else if(customerLV.getSelectionModel().getSelectedItem() == null)
			message.setText("Please Select a customer to Remove");
		else if (c!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(customerLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.removeCustomer(c);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");
				
			}// else { // user chose CANCEL or closed the dialog
				//initData();
			   
			//}
		}

	}

}
