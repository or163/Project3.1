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
	private ListView<Dish> dishV;
	
	@FXML
    private ComboBox<Order> WhichOrder;
	
	@FXML
    private ComboBox<Customer> WhichCust;

//	@FXML
//    void CustSelected(ActionEvent event) {
//		Customer cust = custV.getSelectionModel().getSelectedItem();
//		HashMap<Customer,TreeSet<Order>>ordersByCustomer = Main.restaurant.getOrderByCustomer();
//		System.out.println(ordersByCustomer.get(cust));
//		TreeSet<Order> al = new TreeSet<Order>(Main.restaurant.getOrderByCustomer().get(cust));
//		System.out.println(al);
//		WhichOrder.getItems().addAll(al);
//    }
	
    @FXML
    void OrderSelected(ActionEvent event) {
    	Order theSelected = WhichOrder.getSelectionModel().getSelectedItem();
    	WhichCust.setValue(theSelected.getCustomer());
    	selected.getItems().clear();
    	selected.getItems().addAll(theSelected.getDishes());
    }

	@FXML
    void save(ActionEvent event) {
    	List<Dish> list = selected.getItems();
		ArrayList<Dish> dishes = new ArrayList<>(list);
		if (list == null ||list.isEmpty()) {
			message.setText("Please add at list 1 dish");
			message.setTextFill(Color.RED);
		} else {
			Order theSelected = WhichOrder.getSelectionModel().getSelectedItem();
			theSelected.setCustomer(WhichCust.getValue());
			while(!theSelected.getDishes().isEmpty())
				theSelected.removeDish(theSelected.getDishes().get(0));
			for(Dish d: dishes)
				theSelected.addDish(d);
			message.setText("saved succesfully");
			message.setTextFill(Color.GREEN);
			dishV.getSelectionModel().clearSelection();
		}
    }
		

	public void initData() {
		// TODO Auto-generated method stub
		WhichOrder.getItems().addAll(Main.restaurant.getOrders().values());
		WhichCust.getItems().addAll(Main.restaurant.getCustomers().values());
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
