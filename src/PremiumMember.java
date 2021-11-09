import java.util.*;

public class PremiumMember implements Membership {
	
	private Date expireDate;
	
	public PremiumMember(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public void checkMembership(User user) {
		System.out.println("You can read all manga.");
		System.out.println("Expire Date: " + expireDate.toString());
		System.out.println();
	}
	
	public String toString() {
		return "Premium Member";
	}
	
	public Membership checkValidMembershipStatus() {
		Date currentDate = new Date();
		if(currentDate.after(expireDate)) {
		    return BasicMember.getInstance();
		} else {
			return this;
		}
	}
	
	public void rentManga(User user) {
		System.out.println("You can read all manga.\n");
	}
	
	public void readManga(User user) {
		RentalSystem rs = RentalSystem.getInstance();
		String episode = "";
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nRead Manga (you can read all manga)");
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
