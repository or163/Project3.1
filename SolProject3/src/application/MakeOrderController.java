package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Dish;
import Model.Order;
import Utils.DishType;
import Utils.Expertise;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class MakeOrderController {

	@FXML
	private TableView<Dish> dishesTV;

	@FXML
	private TableColumn<Dish, String> name;

	@FXML
	private TableColumn<Dish, Integer> time;

	@FXML
	private TableColumn<Dish, Double> priceLeft;

	@FXML
	private TableColumn<Dish, String> comps;

	@FXML
	private ComboBox<DishType> type;

	@FXML
	private Label priceLabel;

	@FXML
	private TableView<Dish> selected;

	@FXML
	private TableColumn<Dish, String> dishname;

	@FXML
	private TableColumn<Dish, Double> priceRight;

	@FXML
	private Label messageRight;

	@FXML
	private Label messageLeft;

	private ArrayList<Dish> dishList;

	public ArrayList<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(ArrayList<Dish> dishes) {
		this.dishList = dishes;
	}

	public void initData() {
		dishesTV.setPlaceholder(new Label(""));
		selected.setPlaceholder(new Label("Add dish"));
		type.getItems().addAll(DishType.values());
		name.setCellValueFactory(new PropertyValueFactory<>("dishName"));
		time.setCellValueFactory(new PropertyValueFactory<>("timeToMake"));
		priceLeft.setCellValueFactory(new PropertyValueFactory<>("price"));
		comps.setCellValueFactory(d -> new SimpleStringProperty(
				String.valueOf(Utils.Utils.getProperComponents(d.getValue().getComponenets()))));
	}

	@FXML
	private void getDishes(ActionEvent event) {
		dishesTV.getItems().clear();
		DishType dt = type.getSelectionModel().getSelectedItem();
		Collection<Dish> list = Main.restaurant.getDishes().values();
		for (Dish d : list) {
			if (d.getType().equals(dt))
				dishesTV.getItems().add(d);
		}
	}

	@FXML
	private void addDish(ActionEvent event) {
		if (dishesTV.getSelectionModel().getSelectedItem() == null)
			return;
		selected.getItems().add(dishesTV.getSelectionModel().getSelectedItem());
		dishname.setCellValueFactory(new PropertyValueFactory<>("dishName"));
		priceRight.setCellValueFactory(new PropertyValueFactory<>("price"));
		priceLabel.setText(getPrice(selected.getItems()));
	}

	@FXML
	private void removeDish(ActionEvent event) {
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
		priceLabel.setText(getPrice(selected.getItems()));
	}

	@FXML
	private void addToCart(ActionEvent event) {
		if (selected.getItems().size() != 0) {
			this.dishList = new ArrayList<>();
			for (Dish d : selected.getItems())
				this.dishList.add(d);
			messageRight.setTextFill(Color.GREEN);
			messageRight.setText("Items now are in cart");
			priceLabel.setText("");
			selected.getItems().clear();
		} else {
			messageRight.setTextFill(Color.RED);
			messageRight.setText("There is no item selected");
		}
	}

	@FXML
	private void makeOrder(ActionEvent event) {
		if (selected.getItems().size() != 0) {
			this.dishList = new ArrayList<>();
			for (Dish d : selected.getItems())
				this.dishList.add(d);
			Customer c = LoginController.getCustomer();
			Order o = new Order(c, dishList, null);
			Main.restaurant.addOrder(o);
			messageRight.setTextFill(Color.GREEN);
			messageRight.setText("Ordered successfully");
			priceLabel.setText("");
			selected.getItems().clear();
		}
		else {
			messageRight.setTextFill(Color.RED);
			messageRight.setText("There is no item selected");
		}
		
	}

	private String getPrice(Collection<Dish> dishes) {
		String s = "";
		double sum = 0;
		for (Dish d : dishes)
			sum += d.getPrice();
		s += sum + "¤";
		return s;
	}
}
