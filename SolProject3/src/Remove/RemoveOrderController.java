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

    public void initData(){

		orderLV.getItems().clear();
		for (Order o : Main.restaurant.getOrders().values()) {
			orderLV.getItems().add(o);
		}
	}


	//this Method Initialize the input fields of the class//
	@FXML
	private void remove(ActionEvent event) {
		Order o = null;
		o = orderLV.getSelectionModel().getSelectedItem();
		
		if(orderLV==null)
			message.setText("There are no orders to remove");
		else if(orderLV.getSelectionModel().getSelectedItem() == null)
			message.setText("Please Select a order to Remove");
		else if (o!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(orderLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.removeOrder(o);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");
				
			}// else { // user chose CANCEL or closed the dialog
				//initData();
			   
			//}
		}

	}
}
