package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.TreeSet;

import Audio.sounds;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import Remove.RemoveComponentController;
import Remove.RemoveCookController;
import Remove.RemoveCustomerController;
import Remove.RemoveDAController;
import Remove.RemoveDPController;
import Remove.RemoveDeliveryController;
import Remove.RemoveDishController;
import Remove.RemoveOrderController;
import Utils.Expertise;
import Utils.Gender;
import Utils.SerializableWiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class ManagerController {

	private static int counter = 0;
	private static int counter2 = 0;
	
	@FXML
	BorderPane pannelRoot;
	
	@FXML
	private VBox vbox;

	@FXML
	private AnchorPane anchor;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private TableView<Delivery> deliveriesTV;
	
	@FXML
	private TableColumn<Delivery, Integer> delId;

	@FXML
	private TableColumn<Delivery, String> dp;

	@FXML
	private TableColumn<Delivery, String> da;

	@FXML
	private TableColumn<Delivery, LocalDate> date;
	
	@FXML
	private TableView<Order> ordersTV;

	@FXML
	private TableColumn<Order, Integer> ordId;

	@FXML
	private TableColumn<Order, String> cust;

	@FXML
	private TableColumn<Order, Dish> dishes;

	@FXML
	private TableColumn<Order, String> delivery;
	
	@FXML
    private ImageView Audio;
	
	@FXML
    private MediaView mv;
	

	// Initiate table views of uncompleted deliveries and orders that not added yet to deliveries 
	public void initData() {
		//sounds.clickSound();
		//prepare table view to delivery fields
		delId.setCellValueFactory(new PropertyValueFactory<>("id"));  
		dp.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(
				c.getValue().getDeliveryPerson().getFirstName() +  " " + c.getValue().getDeliveryPerson().getLastName())));
		da.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getArea().getAreaName())));
		date.setCellValueFactory(new PropertyValueFactory<>("deliveredDate"));
		for (Delivery d : Main.restaurant.getDeliveries().values()) {  //Populate deliveries TV with uncompleted deliveries
			if (!d.isDelivered())
				deliveriesTV.getItems().add(d);
		}
		//prepare tavle view to order fields
		ordId.setCellValueFactory(new PropertyValueFactory<>("id"));  
		cust.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(
				c.getValue().getCustomer().getFirstName() +  " " + c.getValue().getCustomer().getLastName())));
		dishes.setCellValueFactory(new PropertyValueFactory<>("dishes"));
		delivery.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(c.getValue().getDelivery())));
		for (Order o : Main.restaurant.getOrders().values()) {  //Populate orders TV with orders that has no delivery
			if (o.getDelivery() == null)
				ordersTV.getItems().add(o);
		}
	}
	
	@FXML
	void goHome(ActionEvent event) throws IOException {
		sounds.clickSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Manager.fxml"));
		Parent p = fx.load();
		ManagerController ctrl = (ManagerController) fx.getController();
		ctrl.initData();
		Scene s = new Scene(p, 700, 500);
		Main.stage.setScene(s);
	}
	
	@FXML
    void GoLogin(ActionEvent event) throws IOException {
		sounds.backgroundMusicMute();
		sounds.bellSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
		Parent p = fx.load();
		Scene s = new Scene(p, 700, 500);
		Main.stage.setScene(s);
    }

	@FXML
	void goAddCook(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddCook.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddCookController ctrl = (AddCookController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goAddCustomer(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddCustomer.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddCustomerController ctrl = (AddCustomerController) fx.getController();
		ctrl.initData();
		pannelRoot.setBottom(null);
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goAddDP(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDeliveryPerson.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDPController ctrl = (AddDPController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goAddComponent(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddComponent.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddDish(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDish.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDishController ctrl = (AddDishController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddOrder(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddOrder.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddOrderController ctrl = (AddOrderController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddDA(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDA.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDAController ctrl = (AddDAController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goAddDelivery(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/AddDelivery.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddDeliveryController ctrl = (AddDeliveryController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRemoveCook(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveCook.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveCookController ctrl = (RemoveCookController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goRemoveCustomer(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveCustomer.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveCustomerController ctrl = (RemoveCustomerController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goRemoveDP(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveDeliveryPerson.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveDPController ctrl = (RemoveDPController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRemoveComponent(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveComponent.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveComponentController ctrl = (RemoveComponentController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRemoveDish(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveDish.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveDishController ctrl = (RemoveDishController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRemoveOrder(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveOrder.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveOrderController ctrl = (RemoveOrderController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRemoveDA(ActionEvent event) throws IOException{
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveDA.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveDAController ctrl = (RemoveDAController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRemoveDelivery(ActionEvent event) throws IOException{
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RemoveDelivery.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveDeliveryController ctrl = (RemoveDeliveryController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGRLDList(ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GRLDishListManager.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GRLDishListManagerController ctrl = (GRLDishListManagerController) fx.getController();
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
	void goOrderWaitingTime (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/OrderWaitingTime.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		OrderWaitingTimeController ctrl = (OrderWaitingTimeController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goDeliver (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Deliver.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		DeliverController ctrl = (DeliverController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goCalcOrderRevenue (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/CalcOrderRevenue.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		CalcOrderRevenueController ctrl = (CalcOrderRevenueController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetDeliveriesByPerson (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetDeliveriesByPerson.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetDeliveriesByPersonController ctrl = (GetDeliveriesByPersonController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetNumberOfDeliveriesPerType (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetNumberOfDeliveriesPerType.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		GetNumberOfDeliveriesPerTypeController ctrl = (GetNumberOfDeliveriesPerTypeController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goRevenueFromExpressDeliveries (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/RevenueFromExpressDeliveries.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
//		RevenueFromExpressDeliveriesController ctrl = (RevenueFromExpressDeliveriesController) fx.getController();
//		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goGetProfitRelation (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/GetProfitRelation.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
//		GetProfitRelationController ctrl = (GetProfitRelationController) fx.getController();
//		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goCreateAIMacine (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/CreateAIMacine.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		CreateAIMacineController ctrl = (CreateAIMacineController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goaddCustomerToBlackList (ActionEvent event) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/addCustomerToBlackList.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		addCustomerToBlackListController ctrl = (addCustomerToBlackListController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goDatabases (ActionEvent event) throws IOException {
		sounds.clickSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/viewDataBases.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		viewDatabasesController ctrl = (viewDatabasesController) fx.getController();
		ctrl.initData();
		pannelRoot.setCenter(pp);
	}

	
	@FXML
	void goAdd (ActionEvent event) throws IOException {
		sounds.clickSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Add.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		AddController ctrl = (AddController) fx.getController();
		ctrl.setPannelRoot(pannelRoot);
		pannelRoot.setCenter(pp);
	}

	@FXML
	void goRemove (ActionEvent event) throws IOException {
		sounds.clickSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Remove.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		RemoveController ctrl = (RemoveController) fx.getController();
		ctrl.setPannelRoot(pannelRoot);
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goEdit (ActionEvent event) throws IOException {
		sounds.clickSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Edit.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		EditController ctrl = (EditController) fx.getController();
		ctrl.setPannelRoot(pannelRoot);
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goQuerries (ActionEvent event) throws IOException {
		sounds.clickSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Querries.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		QuerriesController ctrl = (QuerriesController) fx.getController();
		ctrl.setPannelRoot(pannelRoot);
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void goOut(ActionEvent event) throws IOException {
		sounds.backgroundMusicMute();
		sounds.flashBackSound();
		FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
		Pane p = fx.load();
		AnchorPane pp = (AnchorPane) p;
		pannelRoot.setCenter(pp);
	}
	
	@FXML
	void showMenu(MouseEvent event) {
		sounds.clickSound();
		if(ManagerController.counter % 2 == 0) {
			vbox.setVisible(false);
			anchor.setStyle("-fx-background-color: transparent");
		}
		else {
			vbox.setVisible(true);
			anchor.setStyle("-fx-background-color: darkblue");
			}
		ManagerController.counter++;
	}
	
	@FXML
	private void exitButtonAction(ActionEvent event){
		sounds.backgroundMusicMute();
		sounds.flashBackSound();
		try
		{
		    Thread.sleep(2600);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		Stage stage = (Stage) exitButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	
	@FXML
    void MuteOnOff(MouseEvent event) {
		sounds.clickSound();
		if(ManagerController.counter2  % 2 == 1)
			Audio.setImage(new Image("Icons/audio_64px.png"));
		else
			Audio.setImage(new Image("Icons/no_audio_64px.png"));
		counter2++;
		sounds.backgroundMusic();
    }
	
	@FXML
	void SaveToSerelizebaleFile(ActionEvent event) {
		try {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Save");
			a.setContentText("Are you sure you want to save?");
			Optional<ButtonType> result = a.showAndWait();
			if (result.get() == ButtonType.OK){
				SerializableWiz.save(Main.restaurant);
			} else
			    ;
		}catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
}
