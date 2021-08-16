package Remove;

import java.util.Optional;

import Model.Delivery;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class RemoveDeliveryController {
	
	
    @FXML
    private ListView<Delivery> dellLV;

    @FXML
    private Label message;

    public void initData(){

		dellLV.getItems().clear();
		dellLV.getItems().addAll(Main.restaurant.getDeliveries().values());
	}


	//this Method Initialize the input fields of the class//
	@FXML
	private void remove(ActionEvent event) {
		Delivery d = null;
		d = dellLV.getSelectionModel().getSelectedItem();
		
		if(dellLV==null)
			message.setText("There are no deliveries to remove");
		else if(dellLV.getSelectionModel().getSelectedItem() == null)
			message.setText("Please Select a delivery to Remove");
		else if (d!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.dialogPaneProperty().get().setPrefWidth(600);
			alert.setHeaderText(dellLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.removeDelivery(d);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");
				
			}// else { // user chose CANCEL or closed the dialog
				//initData();
			   
			//}
		}

	}
}