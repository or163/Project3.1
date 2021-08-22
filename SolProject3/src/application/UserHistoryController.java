package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

import Model.Customer;
import Model.Delivery;
import Model.Dish;
import Model.Order;
import Utils.DishType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UserHistoryController {

    @FXML
    private TableView<Order> ordersTV;

    @FXML
    private TableColumn<Order, Integer> id;

    @FXML
    private TableColumn<Order, Dish> dishes;

    @FXML
    private TableColumn<Order, Delivery> delivery;
    
    private static BorderPane border;

    public static void setBorder(BorderPane border) {
		UserHistoryController.border = border;
	}
    
    public void initData() {
    	ordersTV.setPlaceholder(new Label("There are no orders"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		dishes.setCellValueFactory(new PropertyValueFactory<>("dishes"));
		delivery.setCellValueFactory(new PropertyValueFactory<>("delivery"));
		HashMap<Customer,TreeSet<Order>> ordersByCustomer = Main.restaurant.getOrderByCustomer();
		if(ordersByCustomer.get(LoginController.getCustomer()) != null)
			ordersTV.getItems().addAll(ordersByCustomer.get(LoginController.getCustomer()));
    }
    
    @FXML
    private void remove(ActionEvent event) {
    	if(ordersTV.getSelectionModel().getSelectedItem() == null)
    		return;
    	Order o = ordersTV.getSelectionModel().getSelectedItem();
    	Main.restaurant.getOrderByCustomer().get(LoginController.getCustomer()).remove(o);
    	ordersTV.getItems().remove(ordersTV.getSelectionModel().getSelectedItem());
    }
    
    @FXML
	void goMakeOrder(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/MakeOrder.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		MakeOrderController ctrl = (MakeOrderController) fx.getController();
		ctrl.initData();
		border.setCenter(pp);
	}
}
