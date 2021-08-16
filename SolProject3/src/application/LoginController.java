package application;

import java.io.IOException;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LoginController {
	
	//current user in system
	private static Customer customer;

	@FXML
	private Hyperlink register;

	@FXML
	private TextField user;

	@FXML
	private PasswordField passw;

	@FXML
	private Label message;
	
	@FXML
	private Button signIn;
	
	@FXML
    private Line userLine;

    @FXML
    private Line passwLine;

	@FXML
	void login(ActionEvent event) {

		String un = user.getText();
		String pass = passw.getText();
		if (un == null || un.isEmpty())
			message.setText("Username is empty!");
		if (pass == null || pass.isEmpty())
			message.setText("Password is empty!");

		// check user connection
		if (un.equals("manager")) {
			if (pass.equals("manager")) {
				// start admin scene
				try {
					FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Manager.fxml"));
					Parent p = fx.load();
					Scene s = new Scene(p, 700, 500);
					Main.stage.setScene(s);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				passwLine.setStroke(Color.RED);
				message.setText("Incorrect Password");
			}
		} else {
			
			for (Customer c : Main.restaurant.getCustomers().values()) {
				if (c.getUserName().equals(un)) {
					if (c.getPassword().equals(pass)) { // if the password match the user name
						LoginController.customer = c;
						try {
							FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/User.fxml"));
							Parent p = fx.load();
							UserController ctrl = (UserController) fx.getController();
							ctrl.initData(c);

							Scene s = new Scene(p, 700, 500);
							Main.stage.setScene(s);

						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					} else {
						passwLine.setStroke(Color.RED);
						userLine.setStroke(Color.LIGHTBLUE);
						message.setText("Incorrect Password");
					}
				}
				if (!c.getUserName().equals(un) && !un.equals("manager")) {
					userLine.setStroke(Color.RED);
					message.setText("User does not exist"); // no exist user
				}
			}
		}
	}

	@FXML
	public void signup(ActionEvent e) {
		try {
			FXMLLoader fx = new FXMLLoader(getClass().getResource("/View/Register.fxml"));
			Parent p = fx.load();
			RegisterController ctrl = (RegisterController) fx.getController();
			ctrl.initData();
			Scene s = new Scene(p, 700, 500);
			Main.stage.setScene(s);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static Customer getCustomer() {
		return customer;
	}

	public static void setCustomer(Customer customer) {
		LoginController.customer = customer;
	}

}
