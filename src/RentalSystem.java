import java.text.*;
import java.util.*;
import java.util.regex.*;

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
        // need to add some predefined data here
        User staff = new Staff("staffU", "staffP", new Date());
        userList.add(staff);
    }
    
    public void register(String username, String password, Date birthday) throws ExExistUser {
    	for (User user : userList) {
    		if (user.getUsername().equals(username)) {
    			throw new ExExistUser();
    		}
    	}
    	User newUser = new User(username, password, birthday);
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
        for (Manga manga: mangaList) {
        	if (manga.isThisBook(bookIndex, name, category, author, updateDate)) {
        		result.add(manga);
        	}
    	}
    	System.out.println("Found " + result.size() + " Manga(s):");
    	System.out.println("Index\tName\tCategory\tAuthor\tUpdate Date\tNumber of Episodes\n");
    	for (Manga manga : result) {
    		System.out.println(manga.getInfo());
    	}
    	System.out.println();
    }
}
