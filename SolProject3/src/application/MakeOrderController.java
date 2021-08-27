package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import Audio.sounds;
import Exceptions.CantAddObjectException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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

	@FXML
	private Pane editPane;

	@FXML
	private TableView<Component> allComps;

	@FXML
	private TableView<Component> compsInDish;

	@FXML
	private TableColumn<Component, String> compName1;

	@FXML
	private TableColumn<Component, String> compName2;

	private static int first = 1;

	public void initData() {
		editPane.setVisible(false);
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
		sounds.clickSound();
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
		sounds.clickSound();
		if (dishesTV.getSelectionModel().getSelectedItem() == null)
			return;
		selected.getItems().add(dishesTV.getSelectionModel().getSelectedItem());
		dishname.setCellValueFactory(new PropertyValueFactory<>("dishName"));
		priceRight.setCellValueFactory(new PropertyValueFactory<>("price"));
		priceLabel.setText(getPrice(selected.getItems()));
	}

	@FXML
	private void removeDish(ActionEvent event) {
		sounds.clickSound();
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
		priceLabel.setText(getPrice(selected.getItems()));
	}

	@FXML
	private void addToCart(ActionEvent event) {
		sounds.clickSound();
		if (selected.getItems().size() != 0) {
			ArrayList<Dish> list = new ArrayList<>();
			for (Dish d : selected.getItems())
				list.add(d);
			if (MakeOrderController.first > 1)
				ShoppingCartController.getDishList().addAll(list);
			else
				ShoppingCartController.setDishList(list);
			MakeOrderController.first++;
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
		sounds.clickSound();
		if (selected.getItems().size() != 0) {
			ArrayList<Dish> list = new ArrayList<>();
			for (Dish d : selected.getItems())
				list.add(d);
			Customer c = LoginController.getCustomer();  //getting current user in system
			Order o = new Order(c, list, null);
			Alert alert = new Alert(AlertType.CONFIRMATION);  //confirmation alert regarding the order
			alert.setTitle("Order");
			alert.setHeaderText("Are you sure you want to make this order?");
			alert.setContentText(o.toString());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					if (Main.restaurant.addOrder(o)) {
						messageRight.setTextFill(Color.GREEN);
						messageRight.setText("Ordered successfully");
						sounds.bonapatiteSound();
						priceLabel.setText("");
						selected.getItems().clear();
					} else
						throw new CantAddObjectException("Order " + o.getId());
				} catch (CantAddObjectException ex) {
					ex.alertMessage();
				}
			}
		} else {
			messageRight.setTextFill(Color.RED);
			messageRight.setText("There is no item selected");
		}

	}

	@FXML
	private void goEdit(ActionEvent event) {
		sounds.clickSound();
		if (dishesTV.getSelectionModel().getSelectedItem() == null)
			return;
		allComps.getItems().clear();
		compsInDish.getItems().clear();
		compName1.setCellValueFactory(new PropertyValueFactory<>("componentName"));
		compName2.setCellValueFactory(new PropertyValueFactory<>("componentName"));
		allComps.getItems().addAll(Main.restaurant.getComponenets().values());
		compsInDish.getItems().addAll(dishesTV.getSelectionModel().getSelectedItem().getComponenets());
		editPane.setVisible(true);
	}

	@FXML
	private void addComp(ActionEvent event) {
		sounds.clickSound();
		if (allComps.getSelectionModel().getSelectedItem() == null)
			return;
		compsInDish.getItems().add(allComps.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void removeComp(ActionEvent event) {
		sounds.clickSound();
		compsInDish.getItems().remove(compsInDish.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void closeEdit(ActionEvent event) {
		sounds.clickSound();
		editPane.setVisible(false);
	}

	@FXML
	public void saveButton(ActionEvent e) {
		sounds.clickSound();
		if (compsInDish.getItems().size() > 0) {
			ArrayList<Component> components = new ArrayList<>(compsInDish.getItems());
			Dish base = dishesTV.getSelectionModel().getSelectedItem();
			Dish d = new Dish(base.getDishName(), base.getType(), components, base.getTimeToMake());
			Main.restaurant.addDish(d);
			dishesTV.getItems().add(d);

		}
	}

	public static String getPrice(Collection<Dish> dishes) {
		String s = "";
		double sum = 0;
		for (Dish d : dishes)
			sum += d.getPrice();
		s += sum + "�";
		return s;
	}
}
