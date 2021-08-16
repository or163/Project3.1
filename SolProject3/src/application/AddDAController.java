package application;

import java.awt.TextArea;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TimeZone;

import Model.Cook;
import Model.DeliveryArea;
import Model.Restaurant;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddDAController {

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
    
    


//	 public void save(ActionEvent e) {
//			Gender gend = (Gender) gender.getSelectionModel().getSelectedItem();
//			Expertise expert = (Expertise) expertise.getSelectionModel().getSelectedItem();
//			boolean chef = false;
//			if(isChefYes.isSelected())
//				chef = true;
//			else if(isChefNo.isSelected())
//				chef = false;
//			LocalDate bday = date.getValue();
//			Cook cook = new Cook(txtFName.getText(), txtLName.getText(), bday, gend, expert, chef);
//			
//			if(txtFName.getText() == null || txtFName.getText().isEmpty() || gend == null || expert ==null || bday == null ||
//					chefTG.getSelectedToggle()== null) {
//				message.setText("you have fields that are empty");
//			}
//			
//			Main.restaurant.addCook(cook);
//			txtLName.setText("");
//			txtFName.setText("");
//			if (gender.getSelectionModel() != null)
//				gender.getSelectionModel().clearSelection();
//			if(expertise.getSelectionModel()!= null)
//				expertise.getSelectionModel().clearSelection();
//			if (chefTG.getSelectedToggle() != null)
//				chefTG.getSelectedToggle().setSelected(false);
//			initDate();

	 
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
	
//	@FXML
//	private void addComp(ActionEvent e) {
//		selected.getItems().add(comps.getSelectionModel().getSelectedItem());
//	}
//	
//	@FXML
//	private void removeComp(ActionEvent e) {
//		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
//	}
//	
//	private void initDate() {
//		Calendar c = Calendar.getInstance();
//		TimeZone tz = c.getTimeZone();
//		ZoneId id = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
//		LocalDate ld = LocalDateTime.ofInstant(c.toInstant(), id).toLocalDate();
//		date.setDayCellFactory(d -> new DateCell() {
//			public void updateItem(LocalDate locd, boolean empty) {
//				super.updateItem(locd, empty);
//				setDisable(locd.isAfter(ld));
//			};
//		});
////		date.setValue(ld);
//	}

}

