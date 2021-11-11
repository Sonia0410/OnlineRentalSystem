package System;

import java.util.*;

public class CmdReadManga implements Command {
	
	private User user;
	private RentalSystem rs = RentalSystem.getInstance();
	
	public CmdReadManga(User user) {
		this.user = user;
	}
	
	public void execute() {
		String episode = "";
		try {
			Scanner scanner = new Scanner(System.in);
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
					user.readManga(manga, bookIndex, episodeInt);
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