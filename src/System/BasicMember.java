package System;

import java.text.*;
import java.util.*;

public class BasicMember implements Membership {
	
	private static BasicMember instance = new BasicMember();

	public static BasicMember getInstance() {
		return instance;
	}
	
	public void checkMembership(User user) {
		System.out.println("You can rent entire / few episode(s) of a manga (30 coins per episode for 30 days).");
		while (true) {
			try {
				System.out.println();
				System.out.println("Do you want to purchase Premium rental service ($48/month to unlock and read all manga)?");
				System.out.println("Type \"yes\" or \"no\"");
				Scanner scanner = new Scanner(System.in);
				System.out.print("Ans: ");
				String ans = scanner.nextLine();
				System.out.println();
				if (ans.equals("yes")) {
					user.purchasePremiumMembership();
					break;
				} else if (ans.equals("no")) {
					break;
				} else {
					throw new ExInvalidOption();
				}
			} catch (ExInvalidOption e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public String toString() {
		return "Basic Member";
	}
	
	public Membership checkValidMembershipStatus() {
		return this;
	}
	
	public void showRentMangaRule(User user) {
		System.out.println("\nRent Manga (30 coins per episode for 30 days)");
		(new CmdRentManga(user)).execute();
	}
	
	public void rentManga(User user, Manga manga, ArrayList<Integer> episodeList) {
		if (user.deductCoin(30 * episodeList.size())) {
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(currentDate); 
			c.add(Calendar.DATE, 30); // current date + 30 days
			Date expireDate = c.getTime();
			RentalRecord rr = new RentalRecord(user, manga, episodeList, expireDate, RentalRecordType.RENT);
			user.addRentalRecord(rr);
			System.out.println("Rent the manga successfully.\n");
		} else {
			System.out.println("Rent the manga unsuccessfully.\n");
		}
	}
	
	public void showReadMangaRule() {
		System.out.println("\nRead Manga (you can read the manga that is rented and not yet expired)");
	}
	
	public void readManga(User user, Manga manga, String bookIndex, int episode) {
		if (!user.canRead(bookIndex, episode)) {
			if (user.checkTodayIsBirthday()) {
				if (user.getBirthdayGift(manga)) {
					System.out.println(manga.getContent(episode) + "\n");
				} else {
					System.out.println("This episode is not yet rented.\n");
				}
			} else {
				System.out.println("This episode is not yet rented.\n");
			}
		} else {
			System.out.println(manga.getContent(episode) + "\n");
		}
	}
}
