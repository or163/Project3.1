package application;

import java.util.ArrayList;
import java.util.Optional;

import Model.Customer;
import Model.Dish;
import Model.Order;
import Utils.SerializableWiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class ShoppingCartController {

    @FXML
    private TableView<Dish> dishesTV;

    @FXML
    private TableColumn<Dish, String> name;

    @FXML
    private TableColumn<Dish, Integer> time;

    @FXML
    private TableColumn<Dish, Double> price;

    @FXML
    private TableColumn<Dish, String> comps;
    
    @FXML
	private Label message;
    
    @FXML
    private TextField priceField;
    
    private static ArrayList<Dish> dishList;

	public static ArrayList<Dish> getDishList() {
		return dishList;
	}

	public static void setDishList(ArrayList<Dish> dishes) {
		dishList = dishes;
	}

	public void initData() {
		// TODO Auto-generated method stub
		dishesTV.setPlaceholder(new Label("There are no items in cart"));
		name.setCellValueFactory(new PropertyValueFactory<>("dishName"));
		time.setCellValueFactory(new PropertyValueFactory<>("timeToMake"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		comps.setCellValueFactory(d -> new SimpleStringProperty(
				String.valueOf(Utils.Utils.getProperComponents(d.getValue().getComponenets()))));
		if(dishList != null) {
			dishesTV.getItems().addAll(dishList);
			priceField.setText(MakeOrderController.getPrice(dishList));
		}
	}

	@FXML
	private void makeOrder(ActionEvent event) {
		if (dishesTV.getItems().size() != 0) {
			Customer c = LoginController.getCustomer();
			Order o = new Order(c, dishList, null);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Order");
			alert.setHeaderText("Are you sure you want to make this order?");
			alert.setContentText(o.toString());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.restaurant.addOrder(o);
				message.setTextFill(Color.GREEN);
				message.setText("Ordered successfully");
				priceField.setText("");
				dishesTV.getItems().clear();
				dishList.clear();
			} else
			    ;
		}
		else {
			message.setTextFill(Color.RED);
			message.setText("There are no items in cart!");
		}
	}
	
	@FXML
	private void removeDish(ActionEvent event) {
		dishesTV.getItems().remove(dishesTV.getSelectionModel().getSelectedItem());
		priceField.setText(MakeOrderController.getPrice(dishesTV.getItems()));
	}

}
