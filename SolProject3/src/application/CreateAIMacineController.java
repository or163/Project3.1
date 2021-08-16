package application;

import java.util.TreeSet;

import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.ExpressDelivery;
import Model.Order;
import Model.RegularDelivery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class CreateAIMacineController {

    @FXML
    private ChoiceBox<DeliveryPerson> deliveryPersons;

    @FXML
    private ChoiceBox<DeliveryArea> deliveryArea;

    @FXML
    private ListView<Order> orders;

    @FXML
    private ListView<Order> selected;

    @FXML
    private Label lblStatus;

    @FXML
    private ListView<Delivery> outcome;

    @FXML
    void addOrder(ActionEvent event) {
    	if(selected.getItems().contains(orders.getSelectionModel().getSelectedItem())) {
    		lblStatus.setText("Can't contain duplications");
			lblStatus.setTextFill(Color.RED);
    	}
		else if(orders.getSelectionModel().getSelectedItem()==null)
		{
			lblStatus.setText("Please select at list 1 order");
			lblStatus.setTextFill(Color.RED);
		}
		else {
			selected.getItems().add(orders.getSelectionModel().getSelectedItem());
			lblStatus.setText("Order added to the chosen order list");
			lblStatus.setTextFill(Color.BLACK);
		}
    }

    @FXML
    void removeOrder(ActionEvent event) {
    	selected.getItems().remove(selected.getSelectionModel().getSelectedItem());
		lblStatus.setText("Order removed from the chosen order list");
		lblStatus.setTextFill(Color.BLACK);
    }

    @FXML
    void save(ActionEvent event) {
    	DeliveryPerson delPer = deliveryPersons.getSelectionModel().getSelectedItem();
    	DeliveryArea delAre = deliveryArea.getSelectionModel().getSelectedItem();
    	if(delPer == null || delAre == null || selected.getItems().isEmpty()|| selected.getItems() == null)
    		{
    			lblStatus.setText("Please fill all fields");//maybe we should put all as execptions?
    			lblStatus.setTextFill(Color.RED);
    		}
//        	else if(selected.getItems().size() == 1) {
//        		Order o = selected.getItems().get(0);
//        		Delivery d = new ExpressDelivery(delPer,delAre,isDel,o,100,datte);
//        		Main.restaurant.addDelivery(d);
//        	}
        	else {
        	TreeSet<Order> ts = new TreeSet<Order>();
        	TreeSet<Delivery> tsResult = new TreeSet<Delivery>();
    		ts.addAll(selected.getItems());
    		tsResult = Main.restaurant.createAIMacine(delPer, delAre, ts);
    		outcome.getItems().addAll(tsResult);
    		lblStatus.setText("Ai Machine worked successfully");
    		lblStatus.setTextFill(Color.GREEN);
    		deliveryPersons.getSelectionModel().clearSelection();
    		deliveryArea.getSelectionModel().clearSelection();
    		orders.getSelectionModel().clearSelection();
    		selected.getItems().clear();
    		System.out.println(tsResult);
        	}
    }

	public void initData() {
		deliveryPersons.getItems().addAll(Main.restaurant.getDeliveryPersons().values());
		deliveryArea.getItems().addAll(Main.restaurant.getAreas().values());
		orders.getItems().addAll(Main.restaurant.getOrders().values());
		selected.getItems().clear();
	}

}