package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class viewDatabasesController {

    @FXML
    private ComboBox<String> ChosenData;
    
    @FXML
    private ListView<Object> LVdb;

    @FXML
    void GetData(ActionEvent event) {
    	String chosen = ChosenData.getSelectionModel().getSelectedItem();
    	LVdb.getItems().clear();
    	switch(chosen) {
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
    		LVdb.getItems().addAll(Main.restaurant.getCustomers().values());
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

    public void initData() {
		// TODO Auto-generated method stub
    	ChosenData.getItems().addAll("cooks","deliveryPersons","customers","dishes","componenets","orders","deliveries","areas","orderByCustomer","orderByDeliveryArea","blackList");
	}
}
