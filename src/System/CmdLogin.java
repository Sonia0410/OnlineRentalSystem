package System;

import java.util.*;

public class CmdLogin implements Command {
	
	private RentalSystem rs = RentalSystem.getInstance();
	
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your username: ");
		String username = scanner.nextLine();
		System.out.print("Please enter your password: ");
		String password = scanner.nextLine();
        System.out.println();
        try {
        	rs.verifyLogin(username, password);
        } catch (ExInvalidLogin e) {
        	System.out.println(e.getMessage());
        }
	}
}
