package application;

import Model.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GetProfitRelationController {

    @FXML
    private TableView<Dish> dishesTV;

    @FXML
    private TableColumn<Dish, Double> price;

    @FXML
    private TableColumn<Dish, Integer> time;
    
    @FXML
    private TableColumn<Dish, String> name;

    @FXML
    void profitRelation(ActionEvent event) {
    	dishesTV.getItems().clear();
    	name.setCellValueFactory(new PropertyValueFactory<>("dishName"));
    	price.setCellValueFactory(new PropertyValueFactory<>("price"));
		time.setCellValueFactory(new PropertyValueFactory<>("timeToMake"));
		dishesTV.getItems().addAll(Main.restaurant.getProfitRelation());
    }
    


}
