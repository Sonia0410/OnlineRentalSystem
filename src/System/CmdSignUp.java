package System;

import java.text.*;
import java.util.*;

public class CmdSignUp implements Command {
	
	private RentalSystem rs = RentalSystem.getInstance();
	
	// fixed (ensure the date of birth is not from future)
	// fixed (ensure all the input field cannot be empty)
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Please enter a username: ");
			String username = scanner.nextLine();
			if (username.equals("")) {
				System.out.println("\nCannot be empty!\n");
				return;
			}
			System.out.print("Please enter a password: ");
			String password = scanner.nextLine();
			if (password.equals("")) {
				System.out.println("\nCannot be empty!\n");
				return;
			}
			System.out.print("Please enter your date of birth (format: yyyy/MM/dd): ");
			String dob = scanner.nextLine();
	        System.out.println();
        	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        	df.setLenient(false);
        	
//    		Code Refactoring: Rename Variable
        	
//        	Old:
        	
//        	Date birthday = df.parse(dob);
        	
//        	New:
        	
        	Date dateOfBirth = df.parse(dob);
        	
//    		---------------
        	
        	Date currentDate = new Date();
    		if (dateOfBirth.after(currentDate)) {
    			throw new ParseException("", 24);
    		}
        	rs.register(username, password, dateOfBirth);
		} catch (ParseException e) {
        	System.out.println("Invalid date of birth!\n");
        } catch (ExExistUser e) {
			System.out.println(e.getMessage());
        }
	}
}
