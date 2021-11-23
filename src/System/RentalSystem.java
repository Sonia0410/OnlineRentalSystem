package System;

import java.util.*;
import java.util.stream.Collectors;

public class RentalSystem {
    
    private static RentalSystem instance = new RentalSystem();
    private ArrayList<User> userList;
    private ArrayList<Manga> mangaList;

    public static RentalSystem getInstance() {
        return instance;
    }

    private RentalSystem() {
    	userList = new ArrayList<User>();
        mangaList = new ArrayList<Manga>();
    }
    
    // for test script only
    public void addStaff(String username, String password, Date dateOfBirth) {
    	User staff = new Staff(username, password, dateOfBirth);
        userList.add(staff);
    }
    
    // for test script only
    public User addUser(String membership, String username, String password, Date dateOfBirth, int coin) {
    	User user = new User(membership, username, password, dateOfBirth, coin);
        userList.add(user);
        return user;
    }
    
//	Code Refactoring: Rename Variable (birthday --> dateOfBirth)
    public void register(String username, String password, Date dateOfBirth) throws ExExistUser {
    	for (User user : userList) {
    		if (user.getUsername().equals(username)) {
    			throw new ExExistUser();
    		}
    	}
    	User newUser = new User(username, password, dateOfBirth);
    	userList.add(newUser);
    	System.out.println("Successfully joined.\n");
    }
    
    public void verifyLogin(String username, String password) throws ExInvalidLogin {
    	for(User user : userList) {
    		if (user.verifyAccount(username, password)) {
    			user.successLogin();
    			return;
    		}
    	}
    	throw new ExInvalidLogin();
    }
    
    public void addManga(String name, String category, String author, String firstEpisodeContent) {
    	String bookIndex = String.format("%05d", mangaList.size());
    	Manga manga = new Manga(bookIndex, name, category, author, firstEpisodeContent);
    	mangaList.add(manga);
    	System.out.println("Successfully added.\n");
    }
    
    public void updateManga(String bookIndex, String newContent) {
    	try {
    		Manga manga = searchMangaByIndex(bookIndex);
	    	if (manga != null) {
	    		manga.addOneEpisode(newContent);
	    	} else {
	    		throw new ExNoSuchManga();
	    	}
    	} catch (ExNoSuchManga e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public Manga searchMangaByIndex(String bookIndex) {
    	for (Manga manga : mangaList) {
    		if (manga.isThisBook(bookIndex)) {
    			return manga;
    		}
    	}
    	return null;
    }
    
    public void searchManga(String bookIndex, String name, String category, String author, Date updateDate) {
    	ArrayList<Manga> result = new ArrayList<Manga>();
    	
//		Code Refactoring: Replace Loop With Pipeline
    	
//    	Old:
    	
//        for (Manga manga: mangaList) {
//        	if (manga.isThisBook(bookIndex, name, category, author, updateDate)) {
//        		result.add(manga);
//        	}
//    	}
    	
//    	New:
    	
    	result = (ArrayList<Manga>)mangaList.stream()
    			.filter(manga -> manga.isThisBook(bookIndex, name, category, author, updateDate))
    			.collect(Collectors.toList());
    	
//		---------------
    	
    	System.out.println("Found " + result.size() + " Manga(s):");
    	System.out.println("Index\tName\tCategory\tAuthor\tUpdate Date (yyyy/MM/dd)\tNumber of Episodes\n");
    	for (Manga manga : result) {
    		System.out.println(manga.getInfo());
    	}
    	System.out.println();
    }
}
