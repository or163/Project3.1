package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AddController {
	
	private BorderPane pannelRoot;
	
	public void setPannelRoot(BorderPane pannelRoot) {
		this.pannelRoot = pannelRoot;
	}

	@FXML
	void goAddCook(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddCook.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddCookController ctrl = (AddCookController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goAddCustomer(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddCustomer.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddCustomerController ctrl = (AddCustomerController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goAddDP(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDeliveryPerson.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDPController ctrl = (AddDPController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goAddComponent(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddComponent.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddDish(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDish.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDishController ctrl = (AddDishController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddOrder(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddOrder.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddOrderController ctrl = (AddOrderController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddDA(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDA.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDAController ctrl = (AddDAController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddDelivery(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDelivery.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDeliveryController ctrl = (AddDeliveryController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
}
