package application;

import java.io.File;
import java.io.IOException;

import Model.Customer;
import Utils.Gender;
import Utils.Neighberhood;
import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class EditUserController {

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
	private ChoiceBox<Gender> gender;

	@FXML
	private PasswordField passw;

	@FXML
	private Label message;

	@FXML
	private ComboBox<Neighberhood> neighborhood;

	@FXML
	private ImageView profilePic;

	private BorderPane pannel;
	private String imageUrl;
	private ImageView image;

	Customer cust = LoginController.getCustomer();

	public void initData() {
		gender.getItems().clear();
		neighborhood.getItems().clear();
		for (Gender g : Gender.values())
			gender.getItems().add(g);
		for (Neighberhood n : Neighberhood.values())
			neighborhood.getItems().add(n);
		userName.setText(cust.getUserName());
		passw.setText(cust.getPassword());
		txtFName.setText(cust.getFirstName());
		txtLName.setText(cust.getLastName());
		date.setValue(cust.getBirthDay());
		gender.setValue(cust.getGender());
		neighborhood.setValue(cust.getNeighberhood());
		if (cust.getIsSensitiveToLactose())
			lactoseTG.selectToggle(lactoseYes);
		else
			lactoseTG.selectToggle(lactoseNo);
		if (cust.getIsSensitiveToGluten())
			glutenTG.selectToggle(glutenYes);
		else
			glutenTG.selectToggle(glutenNo);
		if (cust.getProfilePicturePath() != null)
			profilePic.setImage(new Image(cust.getProfilePicturePath()));
	}

	@FXML
	void save(ActionEvent event) {
		if (userName.getText() == null || userName.getText().isEmpty() || txtFName.getText() == null
				|| txtFName.getText().isEmpty() || txtLName.getText() == null || txtLName.getText().isEmpty()
				|| lactoseTG.getSelectedToggle() == null || glutenTG.getSelectedToggle() == null) {
			message.setText("you have fields that are empty");
			message.setTextFill(Color.RED);
		} else if (Utils.isValidPassword(passw.getText(), message) == false)
			;
		else {
			cust.setUserName(userName.getText());
			cust.setPassword(passw.getText());
			cust.setFirstName(txtFName.getText());
			cust.setLastName(txtLName.getText());
			cust.setBirthDay(date.getValue());
			cust.setGender(gender.getSelectionModel().getSelectedItem());
			cust.setNeighberhood(neighborhood.getSelectionModel().getSelectedItem());
			if (lactoseYes.isSelected())
				cust.setSensitiveToLactose(true);
			else
				cust.setSensitiveToLactose(false);
			if (glutenYes.isSelected())
				cust.setSensitiveToGluten(true);
			else
				cust.setSensitiveToGluten(false);
			if (imageUrl != null) {
				cust.setProfilePicturePath(imageUrl);
				image.setImage(new Image(imageUrl));
				image.setPreserveRatio(false);
			}
			message.setTextFill(Color.GREEN);
			message.setText("saved succefully");
		}
	}

	@FXML
	public void chooseFile() throws IOException {
		FileChooser fc = new FileChooser();
		File tmp = fc.showOpenDialog(Main.stage);
		if (tmp != null) {
			Image img = new Image("file:///" + tmp.getAbsolutePath());
			imageUrl = "file:///" + tmp.getAbsolutePath();
			profilePic.setImage(img);
		}
	}

	public BorderPane getPannel() {
		return pannel;
	}

	public void setPannel(BorderPane pannel) {
		this.pannel = pannel;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

}
