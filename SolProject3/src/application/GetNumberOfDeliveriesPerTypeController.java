package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GetNumberOfDeliveriesPerTypeController {

	@FXML
	private ComboBox<String> ChosenType;
	
	@FXML
    private Label resultt;
	
	public void initData() {
		ChosenType.getItems().addAll("Regular delivery","Express delivery");
		
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void GetNumberOfDeliveriesPerType(ActionEvent event) {
		String chosen = ChosenType.getSelectionModel().getSelectedItem();
		int num= Main.restaurant.getNumberOfDeliveriesPerType().get(chosen);
		System.out.println(num);
		System.out.println(chosen);
		resultt.setText(String.valueOf(num));
		}
	
}
