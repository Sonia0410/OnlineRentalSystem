import java.util.*;
import java.util.concurrent.TimeUnit;

enum RentalRecordType {
	RENT, BIRTHDAY_GIFT
}

public class RentalRecord {
	
	private User user;
	private Manga manga;
	private ArrayList<Integer> episodesList;
	private Date expireDate;
	private RentalRecordType type;
	
	public RentalRecord(User user, Manga manga, ArrayList<Integer> episodesList, Date expireDate, RentalRecordType type) {
		this.user = user;
		this.manga = manga;
		this.episodesList = episodesList;
		this.expireDate = expireDate;
		this.type = type;
	}
	
	public boolean isExpired(Date currentDate) {
		return currentDate.after(expireDate);
	}
	
	public boolean canRead(String bookIndex, int episode) {
		Date currentDate = new Date();
		if (manga.isThisBook(bookIndex) && !isExpired(currentDate)) {
			for (Integer e : episodesList) {
				if (e == episode) {
					return true;
				}
			}
		}
		return false;
	}
	
	public RentalRecordType getType() {
		return type;
	}
	
	public String getInfo() {
		String episodesString = "";
		for (int i=0; i<episodesList.size(); i++) {
			episodesString = episodesString.concat(String.valueOf(episodesList.get(i)));
			if (i < episodesList.size() - 1) {
				episodesString = episodesString.concat(", ");
			}
		}
		String expireStatus = "";
		if (expireDate.before(new Date())) {
			expireStatus = "Expired ";
		} else {
			Date currentDate = new Date();
	        long diff = expireDate.getTime() - currentDate.getTime();
	        TimeUnit time = TimeUnit.DAYS; 
	        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
	        expireStatus = String.valueOf(difference);
	        expireStatus = expireStatus.concat(" day(s) left ");
		}
		return manga.getBookIndex() + "\t" + manga.getName() + "\t" + episodesString + "\t" + expireStatus + "(" + expireDate.toString() + ")\t" + type.toString();
	}
}
