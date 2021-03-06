package System;

import java.text.*;
import java.util.*;

public class CmdSearchManga implements Command {
	
	private RentalSystem rs = RentalSystem.getInstance();
	
	// fixed (leave the input field blank to ignore)
	// fixed (input update date --> max retry 3 times)
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter Manga index (Leave the input field blank to ignore): ");
		String bookIndex = scanner.nextLine();
		System.out.print("Please enter Manga name (Leave the input field blank to ignore): ");
		String name = scanner.nextLine();
		System.out.print("Please enter Manga category (Leave the input field blank to ignore): ");
		String category = scanner.nextLine();
		System.out.print("Please enter Manga author (Leave the input field blank to ignore): ");
		String author = scanner.nextLine();
		Date updateDate = null;
		for (int i=0; i<=3; i++) {
			try {
				System.out.print("Please enter Manga update date  (format: yyyy/MM/dd) (Leave the input field blank to ignore): ");
				String uDate = scanner.nextLine();
				if (uDate.equals("")) {
					break;
				}
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		    	df.setLenient(false);
		    	updateDate = df.parse(uDate);
		    	break;
			} catch (ParseException e) {
				if (i < 3) {
					System.out.println("Invalid update date! Please try again.");
				} else {
					System.out.println("Invalid update date!\n");
					return;
				}
			}
		}
		System.out.println();
		rs.searchManga(bookIndex, name, category, author, updateDate);
	}
}
