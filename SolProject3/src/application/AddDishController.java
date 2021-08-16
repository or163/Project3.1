package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Component;
import Model.Cook;
import Model.Dish;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddDishController {

    @FXML
    private Label message;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<DishType> type;

    @FXML
    private ListView<Component> comps;
    
    @FXML
    private ListView<Component> selected;

    @FXML
    private TextField time;

    @FXML
	public void save(ActionEvent e) {
		DishType dt = (DishType) type.getSelectionModel().getSelectedItem();
		List<Component> list = selected.getItems();
		ArrayList<Component> comp = new ArrayList<>(list);
		int timeToMake = 0;
		if(!(time.getText().isEmpty()))
			timeToMake = Integer.parseInt(time.getText());
		
		if (name.getText() == null || name.getText().isEmpty() || time.getText() == null || time.getText().isEmpty() ||
				type == null || comps.getSelectionModel().getSelectedItems() == null || list == null) {
			message.setText("you have fields that are empty");
		} else {
			Dish dish = new Dish(name.getText(), dt, comp, timeToMake);
			Main.restaurant.addDish(dish);
			message.setText("saved succesfully");
			name.clear();
			time.clear();
			type.getSelectionModel().clearSelection();
			comps.getSelectionModel().clearSelection();
			selected.getItems().clear();
			System.out.println(Main.restaurant.getDishes());
		}
	}

	public void initData() {
		// TODO Auto-generated method stub
		name.clear();
		time.clear();
		type.getSelectionModel().clearSelection();
		comps.getSelectionModel().clearSelection();
		selected.getItems().clear();
		for (DishType dt : DishType.values())
			type.getItems().add(dt);
		for (Component c : Main.restaurant.getComponenets().values())
			comps.getItems().add(c);
	}
	
	@FXML
	private void addComp(ActionEvent e) {
		selected.getItems().add(comps.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	private void removeComp(ActionEvent e) {
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
	}

}
