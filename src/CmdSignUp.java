import java.text.*;
import java.util.*;

public class CmdSignUp implements Command {
	
private RentalSystem rs = RentalSystem.getInstance();
	
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Please enter a username: ");
			String username = scanner.nextLine();
			System.out.print("Please enter a password: ");
			String password = scanner.nextLine();
			System.out.print("Please enter your date of birth (format: yyyy/MM/dd): ");
			String dob = scanner.nextLine();
	        System.out.println();
        	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        	df.setLenient(false);
        	Date birthday = df.parse(dob);
        	rs.register(username, password, birthday);
		} catch (ParseException e) {
        	System.out.println("Invalid date of birth!\n");
        } catch (ExExistUser e) {
			System.out.println(e.getMessage());
        } 
	}

}
