package System;

import java.util.*;

public interface Membership {
	public void checkMembership(User user);
	public String toString();
	public Membership checkValidMembershipStatus();
	public void showRentMangaRule(User user);
	public void rentManga(User user, Manga manga, ArrayList<Integer> episodeList);
	public void showReadMangaRule();
	public void readManga(User user, Manga manga, String bookIndex, int episode);
}
