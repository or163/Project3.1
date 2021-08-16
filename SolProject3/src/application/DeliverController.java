package application;

import Model.Delivery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class DeliverController {

	@FXML
	private ListView<Delivery> deliveries;

	@FXML
	private Label message;
	
	public void initData() {
		// TODO Auto-generated method stub
		for (Delivery d : Main.restaurant.getDeliveries().values()) {
			if (!d.isDelivered())
				deliveries.getItems().add(d);
		}
	}

	@FXML
	void deliver(ActionEvent event) {
		if (!deliveries.getItems().isEmpty()) {
			Delivery d = deliveries.getSelectionModel().getSelectedItem();
			Main.restaurant.deliver(d);
			deliveries.getItems().remove(d);
		}
		else {
			message.setTextFill(Color.RED);
			message.setText("There are no deliveris on the list!");
		}
	}

}
