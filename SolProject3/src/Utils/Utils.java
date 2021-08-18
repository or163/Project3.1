package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import Model.Component;
import Model.Customer;
import application.Main;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Utils {

	public static void initDate(DatePicker date) {
		Calendar c = Calendar.getInstance();
		TimeZone tz = c.getTimeZone();
		ZoneId id = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		LocalDate ld = LocalDateTime.ofInstant(c.toInstant(), id).toLocalDate();
		date.setDayCellFactory(d -> new DateCell() {
			public void updateItem(LocalDate locd, boolean empty) {
				super.updateItem(locd, empty);
				setDisable(locd.isAfter(ld));
			};
		});
		date.setValue(ld);
	}
	
	public static boolean isOnlyDigits(String str) {
		if(str.length()==0)
			return false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                return false;
        }
        return true;
    }
	
	public static boolean isDouble(String str) {
		if(str.length()==0)
			return false;
		int dotCounter = 0;
        for (int i = 0; i < str.length(); i++) {
        	if(str.charAt(i) =='.')
        		dotCounter++;
        	if(dotCounter>1)
        		return false;
        	if ((str.charAt(i) < '0' || str.charAt(i) > '9' )&& str.charAt(i)!='.')
                return false;
        }
        if(dotCounter>0 && str.length()==1)
        	return false;
        return true;
        	
    }
	
	
	
    public static boolean isValidPassword(String password, Label message)
    {
            boolean isValid = true;
            message.setTextFill(Color.RED);
            if (password.length() > 15 || password.length() < 6)
            {
                    message.setText("Password must be less than 15 and more than 6 characters in length.");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password.matches(upperCaseChars ))
            {
            		message.setText("Password must have atleast one uppercase character");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password.matches(lowerCaseChars ))
            {
            		message.setText("Password must have atleast one lowercase character");
                    isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!password.matches(numbers ))
            {
            		message.setText("Password must have atleast one number");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!password.matches(specialChars ))
            {
            		message.setText("Password must have atleast one special character among @#$%");
                    isValid = false;
            }
            return isValid; 
    }
    
    public static boolean userNameExists(String name) {
    	for(Customer c : Main.restaurant.getCustomers().values()) {
    		if(c.getUserName().equals(name))
    			return true;
    	}
    	return false;		
    }
    
    public static String getProperComponents(List<Component> comps) {
    	Iterator<Component> iterator = comps.iterator();
    	String s = "";
    	while(iterator.hasNext())
    		s += iterator.next().getComponentName() + ". ";
    	return s;
    }
}
