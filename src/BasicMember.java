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
	
	public void rentManga(User user) {
		try {
			RentalSystem rs = RentalSystem.getInstance();
			
			// rent entire / few episode(s) of a manga (30 coins per episode (basic member)) (30 days)
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nRent Manga (30 coins per episode for 30 days)");
			System.out.print("Please enter Manga Index: ");
			String bookIndex = scanner.nextLine();
			Manga manga = rs.searchMangaByIndex(bookIndex);
			if (manga != null) {
				System.out.print("Please enter the episodes that you want to rent (split by |): ");
				String episodes = scanner.nextLine();
				String[] episodesList = episodes.split("|");
				System.out.println();
				ArrayList<Integer> episodesIntList = new ArrayList<Integer>();
				for (int i=0; i<episodesList.length; i++) {
					try {
						int episodeInt = Integer.parseInt(episodesList[i]);
						if (manga.getNumberOfEpisodes() < episodeInt || episodeInt < 1) {
							throw new NumberFormatException();
						} else if (user.canRead(bookIndex, episodeInt)) {
							System.out.println("Episode " + episodeInt + "is still valid to read. You can only rent it after the expire date.");
						} else {
							episodesIntList.add(episodeInt);
						}
					} catch (NumberFormatException e) {
						System.out.println("\"" + episodesList[i] + "\" is not a valid episode.");
					}
				}
				if (!episodesIntList.isEmpty()) {
					Collections.sort(episodesIntList);
					if (user.deductCoin(30 * episodesIntList.size())) {
						Date currentDate = new Date();
						Calendar c = Calendar.getInstance(); 
						c.setTime(currentDate); 
						c.add(Calendar.DATE, 30); // current date + 30 days
						Date expireDate = c.getTime();
						RentalRecord rr = new RentalRecord(user, manga, episodesIntList, expireDate, RentalRecordType.RENT);
						user.addRentalRecord(rr);
						System.out.println("Rent the manga successfully.\n");
					}
				} else {
					System.out.println("Fail to rent the manga.\n");
				}
			} else {
				throw new ExNoSuchManga();
			}
		} catch (ExNoSuchManga e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readManga(User user) {
		RentalSystem rs = RentalSystem.getInstance();
		String episode = "";
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nRead Manga");
			System.out.print("Please enter Manga Index: ");
			String bookIndex = scanner.nextLine();
			Manga manga = rs.searchMangaByIndex(bookIndex);
			if (manga != null) {
				System.out.print("Please enter the episode that you want to read: ");
				episode = scanner.nextLine();
				System.out.println();
				int episodeInt = Integer.parseInt(episode);
				if (manga.getNumberOfEpisodes() < episodeInt || episodeInt < 1) {
					throw new NumberFormatException();
				} else if (!user.canRead(bookIndex, episodeInt)) {
					if (user.checkTodayIsBirthday()) {
						if (user.getBirthdayGift(manga)) {
							System.out.println(manga.getContent(episodeInt) + "\n");
						} else {
							System.out.println("This episode is not yet rented.\n");
						}
					} else {
						System.out.println("This episode is not yet rented.\n");
					}
				} else {
					System.out.println(manga.getContent(episodeInt) + "\n");
				}
			} else {
				throw new ExNoSuchManga();
			}
		} catch (NumberFormatException e) {
			System.out.println("\"" + episode + "\" is not a valid episode.\n");
		} catch (ExNoSuchManga e) {
			System.out.println(e.getMessage());
		}
	}
}
