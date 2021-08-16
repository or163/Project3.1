package application;

import java.time.LocalDate;
import java.util.Collection;

import Model.Customer;
import Model.Dish;
import Utils.Gender;
import Utils.Neighberhood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GRLDishListManagerController {

	@FXML
	private TableView<Customer> customersTV;

	@FXML
	private TableColumn<Customer, Integer> id;

	@FXML
	private TableColumn<Customer, String> fname;

	@FXML
	private TableColumn<Customer, String> lname;

	@FXML
	private TableColumn<Customer, LocalDate> bday;

	@FXML
	private TableColumn<Customer, Gender> gender;

	@FXML
	private TableColumn<Customer, Neighberhood> neigh;

	@FXML
	private TableColumn<Customer, Boolean> lactose;

	@FXML
	private TableColumn<Customer, Boolean> gluten;

	@FXML
	private ListView<Label> dishesLV;

	@FXML
	private Label message;

	public void initData() {
		dishesLV.setVisible(false);
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		bday.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		neigh.setCellValueFactory(new PropertyValueFactory<>("neighberhood"));
		lactose.setCellValueFactory(new PropertyValueFactory<>("isSensitiveToLactose"));
		gluten.setCellValueFactory(new PropertyValueFactory<>("isSensitiveToGluten"));
		for (Customer c : Main.restaurant.getCustomers().values())
			customersTV.getItems().add(c);
	}

	@FXML
	private void getDishList(ActionEvent event) {
		dishesLV.getItems().clear();
		Customer c = customersTV.getSelectionModel().getSelectedItem();
		if (c == null)
			message.setText("Please select a custoemr!");
		else {
			message.setText("");
			dishesLV.setVisible(true);
			Collection<Dish> list = Main.restaurant.getReleventDishList(c);
			for (Dish d : list) {
				Label l = new Label(d.getDishName());
				l.setFont(new Font("Ariel", 16));
				l.setTextFill(Color.CORNFLOWERBLUE);
				dishesLV.getItems().add(l);
			}
		}
	}
}
