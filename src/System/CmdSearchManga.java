package System;

import java.text.*;
import java.util.*;

public class CmdSearchManga implements Command {
	
	private RentalSystem rs = RentalSystem.getInstance();
		
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter Manga index (- for ignore): ");
		String bookIndex = scanner.nextLine();
		System.out.print("Please enter Manga name (- for ignore): ");
		String name = scanner.nextLine();
		System.out.print("Please enter Manga category (- for ignore): ");
		String category = scanner.nextLine();
		System.out.print("Please enter Manga author (- for ignore): ");
		String author = scanner.nextLine();
		Date updateDate = null;
		while(true) {
			try {
				System.out.print("Please enter Manga update date  (format: yyyy/MM/dd) (- for ignore): ");
				String uDate = scanner.nextLine();
				if (uDate.equals("-")) {
					break;
				}
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		    	df.setLenient(false);
		    	updateDate = df.parse(uDate);
		    	break;
			} catch (ParseException e) {
				System.out.println("Invalid update date! Please try again.");
			}
		}
		System.out.println();
		rs.searchManga(bookIndex, name, category, author, updateDate);
	}
}
