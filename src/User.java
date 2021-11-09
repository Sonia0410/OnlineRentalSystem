import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
	
	private ArrayList<RentalRecord> bookshelf;
	private Membership membership;
	protected String username; // = unique ID
	protected String password;
	protected Date birthday;
	private int coin;
	protected Date lastLogin;
	
	public User(String username, String password, Date birthday) {
		this.bookshelf = new ArrayList<RentalRecord>();
		this.membership = BasicMember.getInstance();
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.coin = 120;
		this.lastLogin = new Date();
	}
	
	public boolean verifyAccount(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password) ) {
			return true;
		}
		return false;
	}
	
	public void successLogin() {
		getDailyLoginCoin();
		this.membership = membership.checkValidMembershipStatus();
		System.out.println("Welcome, " + username);
    	Scanner scanner = new Scanner(System.in);
        while (true) {
        	try {
	        	System.out.println("Command:");
	        	System.out.println("1: Logout");
	        	System.out.println("2: Search Manga");
	        	System.out.println("3: Read Manga");
	        	System.out.println("4: Rent Manga");
	        	System.out.println("5: Check My Bookshelf");
	        	System.out.println("6: Purchase Coins");
	        	System.out.println("7: Check Account Info");
	        	System.out.println("8: Check Membership Info");
	        	
	        	System.out.print("\nPlease enter a command (e.g. 1): ");
	        	String command = scanner.nextLine();
	        	System.out.println();
	        	
	        	if (command.equals("1")) {
	        		(new CmdLogout(this)).execute();
	        		break;
		        } else if (command.equals("2")) {
		        	(new CmdSearchManga()).execute();
		        } else if (command.equals("3")) {
		        	membership.readManga(this);
		        } else if (command.equals("4")) {
		        	membership.rentManga(this);
		        } else if (command.equals("5")) {
		        	viewBookshelf();
		        } else if (command.equals("6")) {
		        	(new CmdPurchaseCoinsOptions(this)).execute();
		        } else if (command.equals("7")) {
		        	viewStatus();
		        } else if (command.equals("8")) {
		        	System.out.println("Membership: " + membership.toString());
		        	membership.checkMembership(this);
		        } else {
		        	throw new ExWrongCommand();
		        }
	        } catch (ExWrongCommand e) {
	        	System.out.println(e.getMessage());
	        }
        }
    }
	
	public void logout() {
		lastLogin = new Date();
		System.out.println("Logout successfully.\n");
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean deductCoin(int amount) {
		if (amount <= coin) {
			coin -= amount;
			return true;
		} else {
			System.out.println("Not enough coins.\n");
			return false;
		}
	}
	
	public void addRentalRecord(RentalRecord rr) {
		bookshelf.add(rr);
	}
	
	public boolean canRead(String bookIndex, int episode) {
		for (RentalRecord rr : bookshelf) {
			if (rr.canRead(bookIndex, episode)) {
				return true;
			}
		}
		return false;
	}
	
	public void viewBookshelf() {
		System.out.println("\nFound " + bookshelf.size() + " record(s):");
		System.out.println("Index\tName\tEpisodes\tExpire Date\tRecord Type\n");
    	for (RentalRecord rr : bookshelf) {
    		System.out.println(rr.getInfo());
    	}
    	System.out.println();
	}
	
	public void viewStatus() {
		System.out.println("Membership: " + membership.toString());
    	System.out.println("Username: " + username);
    	System.out.println("Password: " + password);
    	System.out.println("Birthday: " + birthday.toString());
    	System.out.println("Coins: " + coin);
    	System.out.println("Last Login: " + lastLogin.toString());
    	System.out.println();
	}
	
	public void purchaseCoin(int number) {
		this.coin += number;
	}
	
	public void purchasePremiumMembership() {
		Payment payment = Payment.getInstance();
		if (payment.pay(this, 48)) {
			Date expireDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(expireDate); 
			c.add(Calendar.MONTH, 1); // current date + 1 month
			this.membership = new PremiumMember(expireDate);
			System.out.println("Successfully purchased the Premium Membership.\n");
		} else {
			System.out.println("Cannot purchse the Premium Membership.\n");
		}
	}
	
	public void getDailyLoginCoin() {
		Date currentDate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    if (!fmt.format(lastLogin).equals(fmt.format(currentDate))) {
	    	coin += 120;
	    }
	}
	
	public boolean checkTodayIsBirthday() {
		Date currentDate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("MMdd");
	    if (fmt.format(birthday).equals(fmt.format(currentDate))) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	public boolean getBirthdayGift(Manga manga) {
		try {
			for (RentalRecord record : bookshelf) {
				if (record.getType() == RentalRecordType.BIRTHDAY_GIFT && !record.isExpired(new Date())) {
					return false;
				}
			}
			System.out.println("Happy Birthday!");
			System.out.println("This is your free manga for today.\n");
			ArrayList<Integer> episodesIntList = new ArrayList<Integer>();
			for (int i=1; i<=manga.getNumberOfEpisodes(); i++) {
				episodesIntList.add(i);
			}
			Date currentDate = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		    String today = fmt.format(currentDate);
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	df.setLenient(false);
	    	Date expireDate = df.parse(today + " 23:59:59");
			RentalRecord rr = new RentalRecord(this, manga, episodesIntList, expireDate, RentalRecordType.BIRTHDAY_GIFT);
			addRentalRecord(rr);
		} catch (ParseException e) {
			System.out.println("Error occurs when getting birthday gift.\n");
		}
		return true;
	}
}
