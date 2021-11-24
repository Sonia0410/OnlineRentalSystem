package System;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Manga {
	
	private String bookIndex;
	private String name;
	private String category;
	private String author;
	private Date updateDate;
	private int numberOfEpisodes;
	private ArrayList<String> episodesContent;
    
	// fixed (updateDate HH:mm:ss set to 00:00:00 because hiding updateDate hh:mm:ss)
    public Manga(String bookIndex, String name, String category, String author, String firstEpisodeContent) {
        this.bookIndex = bookIndex;
        this.name = name;
        this.category = category;
        this.author = author;
        renewUpdateDate();
        this.numberOfEpisodes = 1;
        this.episodesContent = new ArrayList<String>();
        this.episodesContent.add(firstEpisodeContent);
    }
    
    // fixed (updateDate HH:mm:ss set to 00:00:00 because hiding updateDate hh:mm:ss)
    public void addOneEpisode(String content) {
    	this.numberOfEpisodes += 1;
    	episodesContent.add(content);
    	renewUpdateDate();
    }
    
    // added this method in V4 (fixed)(updateDate HH:mm:ss set to 00:00:00 because hiding updateDate hh:mm:ss)
    public void renewUpdateDate() {
    	Date currentDate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
	    String today = fmt.format(currentDate);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	df.setLenient(false);
    	try {
    		this.updateDate = df.parse(today + " 00:00:00");
    	} catch (ParseException e) {
    		System.out.println("Error in manga update date.");
    	}
    }
    
    public String getContent(int episode) {
    	if (episode <= numberOfEpisodes && episode > 0) {
    		return episodesContent.get(episode - 1);
    	} else {
    		return "Invalid episode!";
    	}
    }
    
    // fixed (hide updateDate hh:mm:ss)
    public String getInfo() {
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	return bookIndex + "\t" + name + "\t" + category + "\t" + author + "\t" + df.format(updateDate) + "\t" + Integer.toString(numberOfEpisodes);
    }
    
    public boolean isThisBook(String bookIndex) {
    	if (this.bookIndex.equals(bookIndex)) {
    		return true;
    	}
    	return false;
    }
    
//	Code Refactoring: Extract Method
    public boolean isThisBook(String bookIndex, String name, String category, String author, Date updateDate) {
    	//regular expression
    	Pattern bookIndexPattern = createSearchPattern(bookIndex);
    	Pattern namePattern = createSearchPattern(name);
    	Pattern categoryPattern = createSearchPattern(category);
    	Pattern authorPattern = createSearchPattern(author);
        
    	if (bookIndexPattern.matcher(this.bookIndex).find()
    		&& namePattern.matcher(this.name).find()
    		&& categoryPattern.matcher(this.category).find()
    		&& authorPattern.matcher(this.author).find()) {
    		if (updateDate != null) {
    			if (updateDate.equals(this.updateDate)) {
    				return true;
    			}
    		} else {
    			return true;
    		}
    	}
    	return false;
    }
    
    //  fixed (leave the input field blank to ignore)
//	Code Refactoring: Extract Method (a new method is added here)
    public Pattern createSearchPattern (String search) {
    	//regular expression
    	if (search.equals("")) {
    		return Pattern.compile("^.{1,}$", Pattern.CASE_INSENSITIVE);
    	} else {
    		return Pattern.compile("^.{0,}" + search + ".{0,}$", Pattern.CASE_INSENSITIVE);
    	}
    }
    
    public String getBookIndex() {
    	return bookIndex;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getNumberOfEpisodes() {
    	return numberOfEpisodes;
    }
}
