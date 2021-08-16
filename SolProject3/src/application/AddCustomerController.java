package application;

import java.time.LocalDate;

import Model.Cook;
import Model.Customer;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddCustomerController {

    @FXML
    private Button saveButton;

    @FXML
    private RadioButton lactoseYes;

    @FXML
    private ToggleGroup lactoseTG;

    @FXML
    private RadioButton lactoseNo;

    @FXML
    private RadioButton glutenYes;

    @FXML
    private ToggleGroup glutenTG;

    @FXML
    private RadioButton glutenNo;

    @FXML
    private TextField userName;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<Gender> gender;

    @FXML
    private ComboBox<Neighberhood> neighborhood;

    @FXML
    private PasswordField passw;

    @FXML
    private Label message;
    
    @FXML
	public void save(ActionEvent e) {
		Gender gend = gender.getSelectionModel().getSelectedItem();
		Neighberhood neigh = neighborhood.getSelectionModel().getSelectedItem();
		boolean lact = false;
		boolean glut = false;
		if (lactoseYes.isSelected())
			lact = true;
		if (glutenYes.isSelected())
			glut = true;
		LocalDate bday = date.getValue();
		
		if (userName.getText() == null || userName.getText().isEmpty()
				||  txtFName.getText() == null || txtFName.getText().isEmpty()
				|| txtLName.getText() == null || txtLName.getText().isEmpty() || gend == null || neigh == null ||
				bday == null || lactoseTG.getSelectedToggle() == null || glutenTG.getSelectedToggle() == null) {
			message.setText("you have fields that are empty");
		}
		else if(Utils.isValidPassword(passw.getText(),message)==false)
			;
		else if(Utils.userNameExists(userName.getText()))
			message.setText("User already exists, choose different user name");
		else {
			Customer cust = new Customer(txtFName.getText(), txtLName.getText(), bday, gend, neigh, lact, glut,
					userName.getText(), passw.getText());
			Main.restaurant.addCustomer(cust);
			message.setText("saved succesfully");
			lactoseTG.getSelectedToggle().setSelected(false);
			glutenTG.getSelectedToggle().setSelected(false);
			userName.clear();
	    	passw.clear();
	    	txtLName.clear();
			txtFName.clear();
			gender.getSelectionModel().clearSelection();
			neighborhood.getSelectionModel().clearSelection();
			Utils.initDate(date);
			System.out.println(bday);
			System.out.println(Main.restaurant.getCustomers());
		}
	}
    
    public void initData() {
		Utils.initDate(date);
		for(Gender g : Gender.values())
			gender.getItems().add(g);
		for(Neighberhood n : Neighberhood.values())
			neighborhood.getItems().add(n);
    }
    

}
