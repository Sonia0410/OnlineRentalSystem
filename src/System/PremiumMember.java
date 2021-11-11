package System;

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
	
	public void showRentMangaRule(User user) {
		System.out.println("\nYou can read all manga.");
		System.out.println("");
	}
	
	public void rentManga(User user, Manga mnga, ArrayList<Integer> episodeList) {
		System.out.println("");
	}
	
	public void showReadMangaRule() {
		System.out.println("\nRead Manga (you can read all manga)");
	}
	
	public void readManga(User user, Manga manga, String bookIndex, int episode) {
		System.out.println(manga.getContent(episode) + "\n");
	}
}
