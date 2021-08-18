package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Component;
import Model.Cook;
import Model.DeliveryPerson;
import Model.Dish;
import Utils.DishType;
import Utils.Expertise;
import Utils.Gender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditDishController {

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
    private ComboBox<Dish> WhichDish;

    @FXML
    void DishSelected(ActionEvent event) {
		name.clear();
		time.clear();
		type.getSelectionModel().clearSelection();
		comps.getSelectionModel().clearSelection();
		selected.getItems().clear();
    	
    	Dish theSelected = WhichDish.getSelectionModel().getSelectedItem();
    	name.setText(theSelected.getDishName());
    	time.setText(theSelected.getTimeToMake()+"");
    	type.setValue(theSelected.getType());
    	selected.getItems().addAll(theSelected.getComponenets());
    	
    	
    }
    
    @FXML
	public void save(ActionEvent e) {
		DishType dt = (DishType) type.getSelectionModel().getSelectedItem();
		List<Component> list = selected.getItems();
		int timeToMake = 0;
		if(!(time.getText().isEmpty())&&Utils.Utils.isOnlyDigits(time.getText()))
			timeToMake = Integer.parseInt(time.getText());
		else {
			message.setText("the time is incorrect");
			message.setTextFill(Color.RED);
		}
		
		if (name.getText() == null || name.getText().isEmpty() || time.getText() == null || time.getText().isEmpty() ||
				type == null || comps.getSelectionModel().getSelectedItems() == null || list == null || list.isEmpty()) {
			message.setText("you have fields that are empty");
			message.setTextFill(Color.RED);
		} else {
			Dish theSelected = WhichDish.getSelectionModel().getSelectedItem();
			theSelected.setDishName(name.getText());
			theSelected.setTimeToMake(timeToMake);
			theSelected.setType(dt);
			while(!theSelected.getComponenets().isEmpty())
				theSelected.removeComponent(theSelected.getComponenets().get(0));
			for(Component c :list) {
				theSelected.addComponent(c);
			}
			message.setText("saved succesfully");
			message.setTextFill(Color.GREEN);
		}
	}

	public void initData() {
		// TODO Auto-generated method stub
		WhichDish.getItems().addAll(Main.restaurant.getDishes().values());
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
