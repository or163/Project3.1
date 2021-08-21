package application;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.ExpressDelivery;
import Model.Order;
import Model.RegularDelivery;
import Utils.Expertise;
import Utils.Gender;
import Utils.Neighberhood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class EditDeliveryController {

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton isDeliverdYes;

    @FXML
    private ToggleGroup deliveyTG;

    @FXML
    private RadioButton isDeliverdNo;

    @FXML
    private Label lblStatus;

    @FXML
    private ListView<Order> orders;

    @FXML
    private ListView<Order> selected;

    @FXML
    private ChoiceBox<DeliveryPerson> deliveryPersons;

    @FXML
    private ChoiceBox<DeliveryArea> deliveryArea;
    
    @FXML
    private ComboBox<Delivery> WhichDel;

    @FXML
    void DelSelected(ActionEvent event) {
		deliveryPersons.getSelectionModel().clearSelection();
		deliveryArea.getSelectionModel().clearSelection();
		orders.getSelectionModel().clearSelection();
		
		selected.getItems().clear();
    	Delivery del = WhichDel.getSelectionModel().getSelectedItem();
    	System.out.println(del);
    	System.out.println(del.getDeliveredDate());
    	date.setValue(del.getDeliveredDate());
    	if (del.isDelivered())
    		deliveyTG.selectToggle(isDeliverdYes);
		else
			deliveyTG.selectToggle(isDeliverdNo);
    	deliveryArea.setValue(del.getArea());
    	deliveryPersons.setValue(del.getDeliveryPerson());
    	if(del instanceof ExpressDelivery)
    		selected.getItems().addAll(((ExpressDelivery) del).getOrder());
    	if(del instanceof RegularDelivery)
    		selected.getItems().addAll(((RegularDelivery) del).getOrders());
	    
    	
    }

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
    	LocalDate datte = date.getValue();
    	boolean isDel = false;
		if (isDeliverdYes.isSelected())
			isDel = true;
		else if (isDeliverdNo.isSelected())
			isDel = false;
    	DeliveryPerson delPer = deliveryPersons.getSelectionModel().getSelectedItem();
    	DeliveryArea delAre = deliveryArea.getSelectionModel().getSelectedItem();
    	Delivery del = WhichDel.getSelectionModel().getSelectedItem();
    	if(delPer == null || delAre == null || selected.getItems().isEmpty()|| selected.getItems() == null 
    		|| datte == null || deliveyTG.getSelectedToggle() == null)
		{
			lblStatus.setText("Please fill all fields");//maybe we should put all as execptions?
			lblStatus.setTextFill(Color.RED);
		}
    	else {
	    	if(selected.getItems().size() == 1 && del instanceof ExpressDelivery) {
	    		del.setArea(delAre);
	    		del.setDelivered(isDel);
	    		del.setDeliveredDate(datte);
	    		del.setDeliveryPerson(delPer);
	    		((ExpressDelivery) del).setOrder(selected.getItems().get(0));
	    	}
	    	else if(selected.getItems().size() == 1 && del instanceof RegularDelivery) {
	    		int id = del.getId();
	    		Main.restaurant.getDeliveries().values().remove(del);
	    		Delivery d = new ExpressDelivery(delPer, delAre, isDel, null, datte);
	    		((ExpressDelivery) d).setOrder(selected.getItems().get(0));
	    		d.setId(id);
	    		Main.restaurant.addDelivery(d);
	    		Delivery.setIdCounter(Delivery.getIdCounter() - 1);
	    	}
	    	else if(selected.getItems().size() > 1 && del instanceof ExpressDelivery) {
	    		int id = del.getId();
	    		Main.restaurant.getDeliveries().values().remove(del);
	    		TreeSet<Order> ts = new TreeSet<Order>();
	    		ts.addAll(selected.getItems());
	    		Delivery d = new RegularDelivery(ts,delPer,delAre,isDel,datte);
	    		d.setId(id);
	    		Main.restaurant.addDelivery(d);
	    		Delivery.setIdCounter(Delivery.getIdCounter() - 1);
	    		
	    	}
	    	else {//RegularDelivery
		    	SortedSet<Order> ss = ((RegularDelivery)del).getOrders();
		    	del.setArea(delAre);
	    		del.setDelivered(isDel);
	    		del.setDeliveredDate(datte);
	    		del.setDeliveryPerson(delPer);
	    		while(!ss.isEmpty())
	    			((RegularDelivery)del).removeOrder(ss.first());
	    		for(Order o: selected.getItems())
	    			((RegularDelivery)del).addOrder(o);
    	}
		lblStatus.setText("Delivery was added successfully");
		lblStatus.setTextFill(Color.GREEN);
//		WhichDel.getItems().clear();
//		WhichDel.getItems().addAll(Main.restaurant.getDeliveries().values());
//		deliveryPersons.getSelectionModel().clearSelection();
//		deliveryArea.getSelectionModel().clearSelection();
//		orders.getSelectionModel().clearSelection();
//		selected.getItems().clear();
//		deliveyTG.getSelectedToggle().setSelected(false);
//		Utils.Utils.initDate(date);
		System.out.println(Main.restaurant.getDeliveries());
    	}
    }
	

    public void initData() {
		// TODO Auto-generated method stub
		deliveryPersons.getItems().addAll(Main.restaurant.getDeliveryPersons().values());
		deliveryArea.getItems().addAll(Main.restaurant.getAreas().values());
		orders.getItems().addAll(Main.restaurant.getOrders().values());
		WhichDel.getItems().addAll(Main.restaurant.getDeliveries().values());
		//orders.getSelectionModel().clearSelection();
//		deliveyTG.getSelectedToggle().setSelected(false);
		selected.getItems().clear();
	}
}

