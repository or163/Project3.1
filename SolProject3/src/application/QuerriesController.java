package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class QuerriesController {

	private BorderPane pannelRoot;
	
	public void setPannelRoot(BorderPane pannelRoot) {
		this.pannelRoot = pannelRoot;
	}

	@FXML
	void goGRLDList(MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GRLDishListManager.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GRLDishListManagerController ctrl = (GRLDishListManagerController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetCooksByExpertise (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetCookByExpertise.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetCookByExpertiseController ctrl = (GetCookByExpertiseController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetPopularComponents (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetPopularComponents.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetPopularComponentsController ctrl = (GetPopularComponentsController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goOrderWaitingTime (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/OrderWaitingTime.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		OrderWaitingTimeController ctrl = (OrderWaitingTimeController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goDeliver (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Deliver.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		DeliverController ctrl = (DeliverController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goCalcOrderRevenue (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/CalcOrderRevenue.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		CalcOrderRevenueController ctrl = (CalcOrderRevenueController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetDeliveriesByPerson (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetDeliveriesByPerson.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetDeliveriesByPersonController ctrl = (GetDeliveriesByPersonController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetNumberOfDeliveriesPerType (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetNumberOfDeliveriesPerType.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetNumberOfDeliveriesPerTypeController ctrl = (GetNumberOfDeliveriesPerTypeController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRevenueFromExpressDeliveries (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RevenueFromExpressDeliveries.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
//		RevenueFromExpressDeliveriesController ctrl = (RevenueFromExpressDeliveriesController) fx.getController();
//		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetProfitRelation (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetProfitRelation.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
//		GetProfitRelationController ctrl = (GetProfitRelationController) fx.getController();
//		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goCreateAIMacine (MouseEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/CreateAIMacine.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		CreateAIMacineController ctrl = (CreateAIMacineController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
}
