package System;

import java.util.*;

public class CmdRentManga implements Command {
	
	private User user;
	private RentalSystem rs = RentalSystem.getInstance();
	
	public CmdRentManga(User user) {
		this.user = user;
	}
	
	public void execute() {
		try {
			Scanner scanner = new Scanner(System.in);
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
					user.rentManga(manga, episodesIntList);
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
}