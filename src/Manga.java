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
    
    public Manga(String bookIndex, String name, String category, String author, String firstEpisodeContent) {
        this.bookIndex = bookIndex;
        this.name = name;
        this.category = category;
        this.author = author;
        this.updateDate = new Date();
        this.numberOfEpisodes = 1;
        this.episodesContent = new ArrayList<String>();
        this.episodesContent.add(firstEpisodeContent);
    }
    
    public void addOneEpisode(String content) {
    	this.numberOfEpisodes += 1;
    	episodesContent.add(content);
    	updateDate = new Date();
    }
    
    public String getContent(int episode) {
    	if (episode <= numberOfEpisodes && episode > 0) {
    		return episodesContent.get(episode - 1);
    	} else {
    		return "Invalid episode!";
    	}
    }
    
    public String getInfo() {
    	return bookIndex + "\t" + name + "\t" + category + "\t" + author + "\t" + updateDate.toString() + "\t" + Integer.toString(numberOfEpisodes);
    }
    
    public boolean isThisBook(String bookIndex) {
    	if (this.bookIndex.equals(bookIndex)) {
    		return true;
    	}
    	return false;
    }
    
    public boolean isThisBook(String bookIndex, String name, String category, String author, Date updateDate) {
    	//regular expression
    	Pattern bookIndexPattern;
    	if (bookIndex.equals("-")) {
    		bookIndexPattern = Pattern.compile("^.{1,}$", Pattern.CASE_INSENSITIVE);
    	} else {
    		bookIndexPattern = Pattern.compile("^.{0,}" + bookIndex + ".{0,}$", Pattern.CASE_INSENSITIVE);
    	}
    	
    	Pattern namePattern;
    	if (name.equals("-")) {
    		namePattern = Pattern.compile("^.{1,}$", Pattern.CASE_INSENSITIVE);
    	} else {
    		namePattern = Pattern.compile("^.{0,}" + name + ".{0,}$", Pattern.CASE_INSENSITIVE);
    	}
    	
    	Pattern categoryPattern;
    	if (category.equals("-")) {
    		categoryPattern = Pattern.compile("^.{1,}$", Pattern.CASE_INSENSITIVE);
    	} else {
    		categoryPattern = Pattern.compile("^.{0,}" + category + ".{0,}$", Pattern.CASE_INSENSITIVE);
    	}
    	
    	Pattern authorPattern;
    	if (author.equals("-")) {
    		authorPattern = Pattern.compile("^.{1,}$", Pattern.CASE_INSENSITIVE);
    	} else {
    		authorPattern = Pattern.compile("^.{0,}" + author + ".{0,}$", Pattern.CASE_INSENSITIVE);
    	}
        
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
