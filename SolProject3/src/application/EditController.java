package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EditController {

	private BorderPane pannelRoot;
	
	public void setPannelRoot(BorderPane pannelRoot) {
		this.pannelRoot = pannelRoot;
	}
	@FXML
	void goEditCook(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditCook.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditCookController ctrl = (EditCookController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goEditCustomer(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditCustomer.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditCustomerController ctrl = (EditCustomerController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goEditDP(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditDeliveryPerson.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditDPController ctrl = (EditDPController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goEditComponent(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditComponent.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditComponentController ctrl = (EditComponentController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goEditDish(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditDish.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditDishController ctrl = (EditDishController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goEditOrder(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditOrder.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditOrderController ctrl = (EditOrderController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goEditDA(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditDA.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditDAController ctrl = (EditDAController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goEditDelivery(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditDelivery.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditDeliveryController ctrl = (EditDeliveryController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
}