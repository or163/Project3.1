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

    public void initData(){

		cookLV.getItems().clear();
		for (Cook c : Main.restaurant.getCooks().values()) {
			cookLV.getItems().add(c);
		}
	}


	//this Method Initialize the input fields of the class//
	@FXML
	private void remove(ActionEvent event) {
		Cook c = null;
		c = cookLV.getSelectionModel().getSelectedItem();
		
		if(cookLV==null)
			message.setText("There are no cooks to remove");
		else if(cookLV.getSelectionModel().getSelectedItem() == null) 
			message.setText("Please Select a bird to Remove");
		else if (c!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(cookLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.removeCook(c);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");
				
			}// else { // user chose CANCEL or closed the dialog
				//initData();
			   
			//}
		}

	}
	
	@FXML
	void DeleteMessage(MouseEvent event) {
		message.setText(null);
	}

}
