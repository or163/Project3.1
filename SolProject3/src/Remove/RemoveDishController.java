package Remove;

import java.util.Optional;

import Model.Dish;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class RemoveDishController {

	@FXML
    private ListView<Dish> dishLV;

    @FXML
    private Label message;

    public void initData(){

		dishLV.getItems().clear();
		for (Dish d : Main.restaurant.getDishes().values()) {
			dishLV.getItems().add(d);
		}
	}


	//this Method Initialize the input fields of the class//
	@FXML
	private void remove(ActionEvent event) {
		Dish d = null;
		d = dishLV.getSelectionModel().getSelectedItem();
		
		if(dishLV==null)
			message.setText("There are no dishes to remove");
		else if(dishLV.getSelectionModel().getSelectedItem() == null)
			message.setText("Please Select a dish to Remove");
		else if (d!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(dishLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.removeDish(d);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");
				
			}// else { // user chose CANCEL or closed the dialog
				//initData();
			   
			//}
		}

	}
}
