package application;

import java.util.HashSet;
import Model.DeliveryArea;
import javafx.event.ActionEvent;
import Utils.Neighberhood;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditDAController {

	@FXML
    private TextField txtAreaName;

    @FXML
    private TextField intDelTime;

    @FXML
    private Label lblStatus;

    @FXML
    private ListView<Neighberhood> listNeigh;
    
    @FXML
    private ListView<Neighberhood> selected;
    

	 
	public void save(ActionEvent e) {
		if(Utils.Utils.isOnlyDigits(intDelTime.getText())) {
			int intDelTime2= Integer.parseInt(intDelTime.getText());
			if(txtAreaName.getText().isEmpty() || intDelTime.getText().isEmpty() || txtAreaName.getText()== null || selected.getItems().isEmpty()|| selected.getItems() == null) 
			{
				lblStatus.setText("Please fill all fields");//maybe we should put all as execptions?
				lblStatus.setTextFill(Color.RED);
			}
			else {
				HashSet<Neighberhood> hs = new HashSet<Neighberhood>();
				hs.addAll(selected.getItems());
				DeliveryArea da = new DeliveryArea(txtAreaName.getText(),hs, intDelTime2);
				lblStatus.setText("Delivery Area was added successfully");
				lblStatus.setTextFill(Color.GREEN);
				Main.restaurant.addDeliveryArea(da);
				initData();
				System.out.println(Main.restaurant.getAreas());
			}
		}
		else {
			lblStatus.setText("Please fill all fields (time as positive number)");
			lblStatus.setTextFill(Color.RED);
		}
		}

	public void initData() {
		txtAreaName.clear();
		intDelTime.clear();
		listNeigh.getSelectionModel().clearSelection();
		listNeigh.getItems().addAll(Neighberhood.values());
		selected.getItems().clear();
	}
	
	public void listviewButtonPushed() {
		if(selected.getItems().contains(listNeigh.getSelectionModel().getSelectedItem())) {
			lblStatus.setText("Can't contain duplications");
			lblStatus.setTextFill(Color.RED);
		}
		else if(listNeigh.getSelectionModel().getSelectedItem()==null)
		{
			lblStatus.setText("Please select at list 1 neighborhood");
			lblStatus.setTextFill(Color.RED);
		}
		else {
			selected.getItems().add(listNeigh.getSelectionModel().getSelectedItem());
			lblStatus.setText("Neighborhood added to the delivery area list");
			lblStatus.setTextFill(Color.BLACK);
		}
		
	}
	
	public void listviewButtonPull() {
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
		lblStatus.setText("Neighborhood removed from the delivery area list");
		lblStatus.setTextFill(Color.BLACK);
		
	}
}