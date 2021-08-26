package application;

import java.util.HashSet;

import Audio.sounds;
import Model.Delivery;
import Model.DeliveryArea;
import javafx.event.ActionEvent;
import Utils.Neighberhood;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditDAController {

	@FXML
    private TextField txtAreaName;

    @FXML
    private Label lblStatus;

    @FXML
    private ListView<Neighberhood> listNeigh;
    
    @FXML
    private ListView<Neighberhood> selected;
    
    @FXML
    private ComboBox<DeliveryArea> WhichDA;

    @FXML
    void DASelected(ActionEvent event) {
    	DeliveryArea da = WhichDA.getSelectionModel().getSelectedItem();
    	listNeigh.getItems().clear();
		selected.getItems().clear();
    	txtAreaName.setText(da.getAreaName());
    	listNeigh.getItems().addAll(Neighberhood.values());
    	listNeigh.getItems().removeAll(da.getNeighberhoods());
    	selected.getItems().addAll(da.getNeighberhoods());
    }

	 
	public void save(ActionEvent e) {
		sounds.clickSound();
			if(txtAreaName.getText().isEmpty()|| txtAreaName.getText()== null || selected.getItems().isEmpty()|| selected.getItems() == null) 
			{
				lblStatus.setText("Please fill up all fields");
				lblStatus.setTextFill(Color.RED);
			}
			else {
				DeliveryArea da = WhichDA.getSelectionModel().getSelectedItem();
				da.setAreaName(txtAreaName.getText());
				for(Neighberhood n: Neighberhood.values())
					da.removeNeighberhood(n);
				for(Neighberhood n: selected.getItems())
					da.addNeighberhood(n);
				lblStatus.setText("Delivery Area was saved successfully");
				lblStatus.setTextFill(Color.GREEN);
				System.out.println(Main.restaurant.getAreas());
			}
		}

	public void initData() {
		WhichDA.getItems().addAll(Main.restaurant.getAreas().values());
		txtAreaName.clear();
		listNeigh.getItems().clear();
		selected.getItems().clear();
	}
	
	public void listviewButtonPushed() {
		sounds.clickSound();
		if(selected.getItems().contains(listNeigh.getSelectionModel().getSelectedItem())) {
			lblStatus.setText("Can't contain duplications");
			lblStatus.setTextFill(Color.RED);
		}
		else if(listNeigh.getSelectionModel().getSelectedItem()==null)
		{
			lblStatus.setText("Please select a neighborhood");
			lblStatus.setTextFill(Color.RED);
		}
		else {
			selected.getItems().add(listNeigh.getSelectionModel().getSelectedItem());
			listNeigh.getItems().remove(listNeigh.getSelectionModel().getSelectedItem());
			lblStatus.setText("Neighborhood added to the delivery area list");
			lblStatus.setTextFill(Color.BLACK);
		}
		
	}
	
	public void listviewButtonPull() {
		sounds.clickSound();
		if(selected.getSelectionModel().getSelectedItem()!=null) {
			selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
		listNeigh.getItems().add(selected.getSelectionModel().getSelectedItem());
		lblStatus.setText("Neighborhood removed from the delivery area list");
		lblStatus.setTextFill(Color.BLACK);
		}
		else {
			lblStatus.setText("Please select a neighborhood");
			lblStatus.setTextFill(Color.RED);
		}
		
	}
}