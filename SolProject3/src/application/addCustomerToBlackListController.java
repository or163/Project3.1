package application;

import java.util.Optional;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class addCustomerToBlackListController {


    @FXML
    private ListView<Customer> customerLV;
    	
    @FXML
    private Label message;

    @FXML
    void AddToBList(ActionEvent event) {
    	Customer c = null;
		c = customerLV.getSelectionModel().getSelectedItem();
		
		if(customerLV==null)
			message.setText("There are no customers to add");
		else if(customerLV.getSelectionModel().getSelectedItem() == null)
			message.setText("Please Select a customer to add");
		else if (c!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(customerLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to add this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.addCustomerToBlackList(c);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Added successfully");
				System.out.println(Main.restaurant.getBlackList());
				
			}
		}
    }
    
    public void initData(){

		customerLV.getItems().clear();
		for (Customer c : Main.restaurant.getCustomers().values()) {
			customerLV.getItems().add(c);
		}
	}

}