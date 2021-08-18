package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import Model.Customer;
import Model.Dish;
import Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class EditOrderController {

	@FXML
	private Label message;

	@FXML
	private ListView<Dish> selected;

	@FXML
	private ComboBox<Customer> custV;

	@FXML
	private ListView<Dish> dishV;
	
	@FXML
    private ComboBox<Order> WhichOrder;

	@FXML
    void CustSelected(ActionEvent event) {
		Customer cust = custV.getSelectionModel().getSelectedItem();
		HashMap<Customer,TreeSet<Order>>ordersByCustomer = Main.restaurant.getOrderByCustomer();
		System.out.println(ordersByCustomer.get(cust));
		ArrayList<Order> al = new ArrayList<Order>(Main.restaurant.getOrderByCustomer().get(cust));
		System.out.println(al);
		WhichOrder.getItems().addAll(al);
    }
	
    @FXML
    void OrderSelected(ActionEvent event) {
    	System.out.println("check");
    	Customer cust = custV.getSelectionModel().getSelectedItem();
    	if(cust == null)
    		message.setText("firstly choose a customer");
//   	WhichOrder.getItems().addAll(Main.restaurant.getOrderByCustomer().get(cust));
    	Order theSelected = WhichOrder.getSelectionModel().getSelectedItem();
    	selected.getItems().clear();
    	selected.getItems().addAll(theSelected.getDishes());
    }

	@FXML
    void save(ActionEvent event) {
    	Customer cust = custV.getSelectionModel().getSelectedItem();
    	List<Dish> list = selected.getItems();
		ArrayList<Dish> dishes = new ArrayList<>(list);
		if (cust == null || list == null) {
			message.setText("you have fields that are empty");
			message.setTextFill(Color.RED);
		} else {
			Order order = new Order(cust, dishes, null);
			if(Main.restaurant.addOrder(order)){
				message.setText("saved succesfully");
				message.setTextFill(Color.GREEN);
				custV.getSelectionModel().clearSelection();
				dishV.getSelectionModel().clearSelection();
				selected.getItems().clear();
				System.out.println(Main.restaurant.getOrders());
			}
			else
				Order.setIdCounter(Order.getIdCounter() - 1);
		}
    }
		

	public void initData() {
		// TODO Auto-generated method stub
		for (Customer c : Main.restaurant.getCustomers().values())
			custV.getItems().add(c);
		for (Dish d : Main.restaurant.getDishes().values())
			dishV.getItems().add(d);
	}

	@FXML
	private void addDish(ActionEvent e) {
		if(dishV.getSelectionModel().getSelectedItem() != null)
			selected.getItems().add(dishV.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void removeDish(ActionEvent e) {
		selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
	}

}
