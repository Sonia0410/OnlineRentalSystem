package System;

import java.util.*;
import Test.*;

public class Main {
	
    public static void main(String[] args) {
    	TestScriptReader.execute();
    	RentalSystem rs = RentalSystem.getInstance();
    	
    	System.out.println("Welcome!\n");
    	Scanner scanner = new Scanner(System.in);
        while (true) {
        	try {
        		System.out.println("Command:");
        		System.out.println("1: Login");
        		System.out.println("2: Sign Up");
        		System.out.println("3: Search Manga");
        		System.out.println("4: Exit");
	        	
        		System.out.print("\nPlease enter a command (e.g. 1): ");
	        	String command = scanner.nextLine();
	        	System.out.println();
	        	
	        	if (command.equals("1")) {
	        		(new CmdLogin()).execute();
		        } else if (command.equals("2")) {
		        	(new CmdSignUp()).execute();
		        } else if (command.equals("3")) {
		        	(new CmdSearchManga()).execute();
		        } else if (command.equals("4")) {
		        	System.out.println("Exit, bye.");
		        	break;
		        } else {
		        	throw new ExWrongCommand();
		        }
	        } catch (ExWrongCommand e) {
	        	System.out.println(e.getMessage());
	        }
        }
    }
}
