package Remove;

import java.util.Optional;

import Model.Component;
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

public class RemoveComponentController {
	
	@FXML
    private ListView<Component> compLV;

    @FXML
    private Label message;

    @FXML
    private TextField id;
    
    public void initData(){

		compLV.getItems().clear();
		for (Component c : Main.restaurant.getComponenets().values()) {
			compLV.getItems().add(c);
		}
	}

    @FXML
    private void getComponent(ActionEvent event) {
        if (!Utils.Utils.isOnlyDigits(id.getText()))
        	return;
        Component comp = Main.restaurant.getRealComponent(Integer.parseInt(id.getText()));
    	compLV.getSelectionModel().select(comp);
    }

	//this Method Initialize the input fields of the class//
	@FXML
	private void remove(ActionEvent event) {
		Component c = null;
		c = compLV.getSelectionModel().getSelectedItem();
		
		if(compLV==null)
			message.setText("There are no components to remove");
		else if(compLV.getSelectionModel().getSelectedItem() == null)
			message.setText("Please Select a component to Remove");
		else if (c!= null){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(compLV.getSelectionModel().getSelectedItem() + " has been chosen");
			alert.setContentText("Are you sure you want to delete this one?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.removeComponent(c);
				initData();
				message.setTextFill(Color.GREEN);
				message.setText("Removed successfully");
				
			}// else { // user chose CANCEL or closed the dialog
				//initData();
			   
			//}
		}

	}
}
