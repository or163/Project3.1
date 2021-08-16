package application;

import Model.Delivery;
import Model.DeliveryPerson;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GetDeliveriesByPersonController {

	@FXML
	private ComboBox<DeliveryPerson> dp;

	@FXML
	private ComboBox<Integer> months;

	@FXML
	private ListView<Delivery> deliveries;

	@FXML
	private Label message;

	public void initData() {
		dp.getItems().addAll(Main.restaurant.getDeliveryPersons().values());
		for (int i = 1; i <= 12; i++)
			months.getItems().add(i);
	}

	@FXML
	private void go() {
		if (dp.getSelectionModel().getSelectedItem() == null)
			message.setText("You must choose a delivery person");
		else if (months.getSelectionModel().getSelectedItem() == null)
			message.setText("You must choose a month");
		else {
			message.setText("");
			deliveries.getItems().clear();
			DeliveryPerson d = dp.getSelectionModel().getSelectedItem();
			int month = months.getSelectionModel().getSelectedItem();
			deliveries.getItems().addAll(Main.restaurant.getDeliveriesByPerson(d, month));
		}
	}

}
