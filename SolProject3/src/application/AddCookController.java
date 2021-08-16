package application;


import java.time.LocalDate;


import Model.Cook;
import Model.Restaurant;
import javafx.event.ActionEvent;
import Utils.Expertise;
import Utils.Gender;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddCookController {

	@FXML
	private TextField txtFName;

	@FXML
	private TextField txtLName;

	@FXML
	private DatePicker date;

	@FXML
	private ChoiceBox<Gender> gender;

	@FXML
	private ChoiceBox<Expertise> expertise;

	@FXML
	private RadioButton isChefYes;

	@FXML
	private RadioButton isChefNo;

	@FXML
	private ToggleGroup chefTG;

	@FXML
	private Label message;

	@FXML
	public void save(ActionEvent e) {
		Gender gend = gender.getSelectionModel().getSelectedItem();
		Expertise expert = expertise.getSelectionModel().getSelectedItem();
		boolean chef = false;
		if (isChefYes.isSelected())
			chef = true;
		else if (isChefNo.isSelected())
			chef = false;
		LocalDate bday = date.getValue();
		
		if (txtFName.getText() == null || txtFName.getText().isEmpty() || txtLName.getText() == null || txtLName.getText().isEmpty() ||
				gend == null || expert == null || bday == null || chefTG.getSelectedToggle() == null) {
			message.setText("you have fields that are empty");
		} else {
			Cook cook = new Cook(txtFName.getText(), txtLName.getText(), bday, gend, expert, chef);
			Main.restaurant.addCook(cook);
			message.setText("saved succesfully");
			chefTG.getSelectedToggle().setSelected(false);
			txtLName.clear();
			txtFName.clear();
			gender.getSelectionModel().clearSelection();
			expertise.getSelectionModel().clearSelection();
			Utils.Utils.initDate(date);
			System.out.println(Main.restaurant.getCooks());
		}
	}

	public void initData() {
		// TODO Auto-generated method stub
		Utils.Utils.initDate(date);
		for (Gender g : Gender.values())
			gender.getItems().add(g);
		for (Expertise e : Expertise.values())
			expertise.getItems().add(e);
	}

}