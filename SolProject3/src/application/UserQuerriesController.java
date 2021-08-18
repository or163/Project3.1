package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UserQuerriesController {

    @FXML
    private ComboBox<String> querries;

    @FXML
    private BorderPane border;

    public void initData() {
    	querries.getItems().addAll("Get Cook By Expert", "Get Relevant Dish List", "Get Popular Component");
    }
    
    @FXML
    void getPage(ActionEvent event) throws IOException {
    	Pane p;
    	AnchorPane pp;
    	FXMLLoader fx;
    	String chosen = querries.getSelectionModel().getSelectedItem();
    	switch(chosen) {
    	case "Get Cook By Expert":
    		fx = new FXMLLoader(getClass().getResource("/View/GetCookByExpertise.fxml"));
    		p = fx.load();
    		pp = (AnchorPane) p;
    		GetCookByExpertiseController ctrl1 = (GetCookByExpertiseController) fx.getController();
    		ctrl1.initData();
    		border.setCenter(pp);
    		break;
    	case "Get Relevant Dish List":
    		fx = new FXMLLoader(getClass().getResource("/View/GRLDishListUser.fxml"));
    		p = fx.load();
    		pp = (AnchorPane) p;
    		GRLDishListUserController ctrl2 = (GRLDishListUserController) fx.getController();
    		ctrl2.initData();
    		border.setCenter(pp);
    		break;
    	case "Get Popular Component":
    		fx = new FXMLLoader(getClass().getResource("/View/GetPopularComponents.fxml"));
    		p = fx.load();
    		pp = (AnchorPane) p;
    		GetPopularComponentsController ctrl3 = (GetPopularComponentsController) fx.getController();
    		ctrl3.initData();
    		border.setCenter(pp);
    		break;
    	}
	}
    
}
