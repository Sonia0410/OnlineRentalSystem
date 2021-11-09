import java.util.*;

public class Staff extends User {
	
	public Staff(String username, String password, Date birthday) {
		super(username, password, birthday);
	}
	
	public void successLogin() {
		System.out.println("Welcome, " + username);
    	Scanner scanner = new Scanner(System.in);
        while (true) {
        	try {
	        	System.out.println("Command:");
	        	System.out.println("1: Logout");
	        	System.out.println("2: Search Manga");
	        	System.out.println("3: Add Manga");
	        	System.out.println("4: Update Manga");
	        	
	        	System.out.print("\nPlease enter a command (e.g. 1): ");
	        	String command = scanner.nextLine();
	        	System.out.println();
	        	
	        	if (command.equals("1")) {
	        		(new CmdLogout(this)).execute();
	        		break;
		        } else if (command.equals("2")) {
		        	(new CmdSearchManga()).execute();
		        } else if (command.equals("3")) {
		        	(new CmdAddManga()).execute();
		        } else if (command.equals("4")) {
		        	(new CmdUpdateManga()).execute();
		        } else {
		        	throw new ExWrongCommand();
		        }
	        } catch (ExWrongCommand e) {
	        	System.out.println(e.getMessage());
	        }
        }
    }
}
