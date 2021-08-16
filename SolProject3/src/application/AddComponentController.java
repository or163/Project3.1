package application;

import Model.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddComponentController {

	@FXML
	private TextField name;

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
	private TextField price;

	@FXML
	private Label message;

	@FXML
	void save(ActionEvent event) {
		boolean lact = false;
		boolean glut = false;
		if (lactoseYes.isSelected())
			lact = true;
		if (glutenYes.isSelected())
			glut = true;
		double cost = 0;
		if (name.getText() == null || name.getText().isEmpty() || price.getText() == null || price.getText().isEmpty()
				|| lactoseTG.getSelectedToggle() == null || glutenTG.getSelectedToggle() == null) {
			message.setText("you have fields that are empty");
		} else {
			if (!(price.getText().isEmpty()) && Utils.Utils.isOnlyDigits(price.getText())) {
				cost = Double.parseDouble(price.getText());
				Component comp = new Component(name.getText(), lact, glut, cost);
				Main.restaurant.addComponent(comp);
				message.setText("saved succesfully");
				lactoseTG.getSelectedToggle().setSelected(false);
				glutenTG.getSelectedToggle().setSelected(false);
				name.clear();
				price.clear();
				System.out.println(Main.restaurant.getComponenets());
			}
		}
	}

}
