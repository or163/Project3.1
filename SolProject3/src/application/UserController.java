package application;

import java.io.IOException;

import Model.Customer;
import Utils.SerializableWiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UserController {

	@FXML
	private BorderPane pannelRoot;

	@FXML
	private Label welcome;
	
	@FXML
	private VBox vbox;
	
	@FXML
	private AnchorPane anchor;
	
	private static int counter = 0;

	public void initData(Customer c) {
		welcome.setText("Welcome " + c.getFirstName());
	}
	
	@FXML
	void goHome(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/User.fxml"));
		Parent p = fx.load();
		Scene s = new Scene(p, 700, 500);
		Main.stage.setScene(s);
	}
	
	@FXML
    void GoLogin(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
		Parent p = fx.load();
		Scene s = new Scene(p, 700, 500);
		Main.stage.setScene(s);
    }
	
	@FXML
	void goMakeOrder(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/MakeOrder.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		MakeOrderController ctrl = (MakeOrderController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	private void goEdit(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/EditUser.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditUserController ctrl = (EditUserController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGRLDList(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GRLDishListUser.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GRLDishListUserController ctrl = (GRLDishListUserController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetCooksByExpertise (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetCookByExpertise.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetCookByExpertiseController ctrl = (GetCookByExpertiseController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetPopularComponents (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetPopularComponents.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetPopularComponentsController ctrl = (GetPopularComponentsController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void showMenu(MouseEvent event) {
		if(UserController.counter % 2 == 0) {
			vbox.setVisible(false);
			anchor.setStyle("-fx-background-color: transparent");
		}
		else {
			vbox.setVisible(true);
			anchor.setStyle("-fx-background-color: #171717");
			}
		UserController.counter++;
	}
	
	@FXML
    private void SaveToSerelizebaleFile(ActionEvent event) {
    	try {
			SerializableWiz.save(Main.restaurant);
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Save");
			a.setContentText("you have saved your file to Rest.Ser");
			a.show();
		}catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
    }
}