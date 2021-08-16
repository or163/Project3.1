package application;

import java.util.ArrayList;
import java.util.LinkedList;

import Model.Component;
import Model.Cook;
import Utils.Expertise;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GetPopularComponentsController {

	@FXML
	private TableView<Component> compsTV;

	@FXML
	private TableColumn<Component, Integer> pop;

	@FXML
	private TableColumn<Component, Integer> id;

	@FXML
	private TableColumn<Component, String> name;

	@FXML
	private TableColumn<Component, String> lactose;

	@FXML
	private TableColumn<Component, String> gluten;

	@FXML
	private TableColumn<Component, Double> price;

	@FXML
	public void initData() {
		LinkedList<Component> list = Main.restaurant.getPopularComponents();
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("componentName"));
		lactose.setCellValueFactory(new PropertyValueFactory<>("hasLactose"));
		gluten.setCellValueFactory(new PropertyValueFactory<>("hasGluten"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		pop.setCellFactory(col -> {
		      TableCell<Component, Integer> indexCell = new TableCell<>();
		      ReadOnlyObjectProperty<TableRow<Component>> rowProperty = indexCell.tableRowProperty();
		      ObjectBinding<String> rowBinding = Bindings.createObjectBinding(() -> {
		        TableRow<Component> row = rowProperty.get();
		        if (row != null) {
		          int rowIndex = row.getIndex() + 1;
		          if (rowIndex <= row.getTableView().getItems().size()) {
		            return Integer.toString(rowIndex);
		          }
		        }
		        return null;
		      }, rowProperty);
		      indexCell.textProperty().bind(rowBinding);
		      return indexCell;
		    });
		for (Component c : list) {
			compsTV.getItems().add(c);
		}
	}
}
