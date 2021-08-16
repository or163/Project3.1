package application;

import javafx.stage.Stage;

import java.io.IOException;

import Model.Restaurant;
import Utils.SerializableWiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
//import static application.Utils.Utils.LoadFXML;

public class Main extends Application {

	public static Restaurant restaurant;
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent p = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
			Scene scene = new Scene(p, 700, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Restaurant");
			primaryStage.show();
			stage = primaryStage;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		//Restaurant restaurant;
		try {
			restaurant = SerializableWiz.read();
			if (restaurant == null) {
				restaurant = Restaurant.getInstance();
			}
			restaurant.SetAllCounters();
			launch(args);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			restaurant = Restaurant.getInstance();
			launch(args);

		}
	}
}