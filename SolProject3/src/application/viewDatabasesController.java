package application;

import Audio.sounds;
import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class viewDatabasesController {

	@FXML
	private ComboBox<String> ChosenData;

	@FXML
	private ListView<Object> LVdb;

	@FXML
	private TextField id;

	@FXML
	private ImageView custPicture;

	// Initiate combo box with all object options
	public void initData() {
		// TODO Auto-generated method stub
		ChosenData.getItems().addAll("cooks", "deliveryPersons", "customers", "dishes", "componenets", "orders",
				"deliveries", "areas", "orderByCustomer", "orderByDeliveryArea", "blackList");
	}

	@FXML // fill the list view with relevant objects according to user selection
	void GetData(ActionEvent event) {
		sounds.clickSound();
		String chosen = ChosenData.getSelectionModel().getSelectedItem();
		LVdb.getItems().clear(); // clean former objects from list every selection
		switch (chosen) {
		case "cooks":
			LVdb.getItems().addAll(Main.restaurant.getCooks().values());
			break;
		case "deliveryPersons":
			LVdb.getItems().addAll(Main.restaurant.getDeliveryPersons().values());
			break;
		case "customers":
			LVdb.getItems().addAll(Main.restaurant.getCustomers().values());
			break;
		case "dishes":
			LVdb.getItems().addAll(Main.restaurant.getDishes().values());
			break;
		case "componenets":
			LVdb.getItems().addAll(Main.restaurant.getComponenets().values());
			break;
		case "orders":
			LVdb.getItems().addAll(Main.restaurant.getOrders().values());
			break;
		case "deliveries":
			LVdb.getItems().addAll(Main.restaurant.getDeliveries().values());
			break;
		case "areas":
			LVdb.getItems().addAll(Main.restaurant.getAreas().values());
			break;
		case "orderByCustomer":
			LVdb.getItems().addAll(Main.restaurant.getOrderByCustomer().values());
			break;
		case "orderByDeliveryArea":
			LVdb.getItems().addAll(Main.restaurant.getOrderByDeliveryArea().values());
			break;
		case "blackList":
			LVdb.getItems().addAll(Main.restaurant.getBlackList());
			break;
		}
	}

	@FXML // filter Objects and get relevant object by Id
	private void getObject(ActionEvent event) {
		sounds.clickSound();
		if (!Utils.Utils.isOnlyDigits(id.getText()))
			return;
		if(ChosenData.getSelectionModel().getSelectedItem() == null)
			return;
		String chosen = ChosenData.getSelectionModel().getSelectedItem();
		switch (chosen) {
		case "cooks":
			Cook cook = Main.restaurant.getRealCook(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(cook);
			break;
		case "deliveryPersons":
			DeliveryPerson dp = Main.restaurant.getRealDeliveryPerson(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(dp);
			break;
		case "customers": // also show customer picture (if exists)
			Customer cust = Main.restaurant.getRealCustomer(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(cust);
			custPicture.setPreserveRatio(false);
			if (cust != null) {
				if (cust.getProfilePicturePath() != null) { // check if picture exists
					custPicture.setImage(new Image(cust.getProfilePicturePath()));
				} else
					custPicture.setImage(new Image("/Icons/no_image_64px.png"));
			}
			break;
		case "dishes":
			Dish dish = Main.restaurant.getRealDish(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(dish);
			break;
		case "componenets":
			Component comp = Main.restaurant.getRealComponent(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(comp);
			break;
		case "orders":
			Order order = Main.restaurant.getRealOrder(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(order);
			break;
		case "deliveries":
			Delivery del = Main.restaurant.getRealDelivery(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(del);
			break;
		case "areas":
			DeliveryArea da = Main.restaurant.getRealDeliveryArea(Integer.parseInt(id.getText()));
			LVdb.getSelectionModel().select(da);
			break;
		}
	}

	@FXML // show customer profile picture (if exists)
	private void showCustomerImage() {
		if (ChosenData.getSelectionModel().getSelectedItem() == "customers") { // validates we are on right property,
																				// customers
			if (LVdb.getSelectionModel().getSelectedItem() != null) {
				Customer c = (Customer) LVdb.getSelectionModel().getSelectedItem();
				custPicture.setPreserveRatio(false);
				if (c.getProfilePicturePath() != null) { // check if picture exists
					custPicture.setImage(new Image(c.getProfilePicturePath()));
				} else
					custPicture.setImage(new Image("/Icons/no_image_64px.png"));
			}
		} else
			custPicture.setImage(null); // in case we are not in customers property, reset picture

	}
}
