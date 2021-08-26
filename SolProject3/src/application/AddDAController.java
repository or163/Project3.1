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

import Exceptions.CantAddObjectException;
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

	public void initData() {
		txtAreaName.clear();
		intDelTime.clear();
		listNeigh.getSelectionModel().clearSelection();
		listNeigh.getItems().addAll(Neighberhood.values());
		selected.getItems().clear();
	}
	
	public void save(ActionEvent e) {
		if (Utils.Utils.isOnlyDigits(intDelTime.getText())) { //if input is in correct format - digits
			int intDelTime2 = Integer.parseInt(intDelTime.getText());
			try {
				if (txtAreaName.getText().isEmpty() || intDelTime.getText().isEmpty() || txtAreaName.getText() == null
						|| selected.getItems().isEmpty() || selected.getItems() == null) {
					lblStatus.setText("Please fill all fields");
					lblStatus.setTextFill(Color.RED);
				} else {
					HashSet<Neighberhood> hs = new HashSet<Neighberhood>();
					hs.addAll(selected.getItems());
					DeliveryArea da = new DeliveryArea(txtAreaName.getText(), hs, intDelTime2);
					lblStatus.setText("Delivery Area was added successfully");
					lblStatus.setTextFill(Color.GREEN);
					if (Main.restaurant.addDeliveryArea(da)) {  //if add succeeds ,clear all fields for further adding
						initData();
					} else
						throw new CantAddObjectException("Delivery Area " + da.getAreaName());
				}
			} catch (CantAddObjectException ex) {
				ex.alertMessage();
			}
		} else {
			lblStatus.setText("Please enter time as positive integer number)");
			lblStatus.setTextFill(Color.RED);
		}
	}

	// add neighborhood to selected neighborhood list view
	public void listviewButtonPushed() {
		if (selected.getItems().contains(listNeigh.getSelectionModel().getSelectedItem())) {
			lblStatus.setText("Can't contain duplications");
			lblStatus.setTextFill(Color.RED);
		} else if (listNeigh.getSelectionModel().getSelectedItem() == null) {
			lblStatus.setText("Please select at list 1 neighborhood");
			lblStatus.setTextFill(Color.RED);
		} else {
			selected.getItems().add(listNeigh.getSelectionModel().getSelectedItem());
			lblStatus.setText("Neighborhood added to the delivery area list");
			lblStatus.setTextFill(Color.BLACK);
		}

	}

	// remove neighborhood from selected neighborhoods list view
	public void listviewButtonPull() {
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
		lblStatus.setText("Neighborhood removed from the delivery area list");
		lblStatus.setTextFill(Color.BLACK);

	}
}
