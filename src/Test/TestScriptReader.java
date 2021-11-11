package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import System.*;

public class TestScriptReader {
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void execute() {
		staff();
		user();
		manga();
		rentalRecord();
	}
	
	private static void staff() {
		// e.g.: testStaff001,1234567,2000/1/27
		
		// username,password,birthday
		
		RentalSystem rs = RentalSystem.getInstance();
		try {
			String filepathname = "D:\\2021_22_SemA\\CS3343_Software_Engineering_Practice\\Project\\Group21\\staffDB.txt";
			
			Scanner inFile = new Scanner(new File(filepathname));
			while (inFile.hasNextLine()) {
				String cmdLine = inFile.nextLine();
				String[] cmdParts = cmdLine.split(",");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		      	df.setLenient(false);
		      	Date birthday = df.parse(cmdParts[2]);
				rs.addStaff(cmdParts[0], cmdParts[1], birthday);
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (ParseException e) {
			System.out.println("Invalid date");
		}
	}
	
	private static void user() {
		// e.g.: Basic,test001,1234567,2000/1/27,200
		
		// membership,username,password,birthday,coin
		
		RentalSystem rs = RentalSystem.getInstance();
		try {
			String filepathname = "D:\\2021_22_SemA\\CS3343_Software_Engineering_Practice\\Project\\Group21\\userDB.txt";
			
			Scanner inFile = new Scanner(new File(filepathname));
			while (inFile.hasNextLine()) {
				String cmdLine = inFile.nextLine();
				String[] cmdParts = cmdLine.split(",");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		      	df.setLenient(false);
		      	Date birthday = df.parse(cmdParts[3]);
				users.add(rs.addUser(cmdParts[0], cmdParts[1], cmdParts[2], birthday, Integer.parseInt(cmdParts[4])));
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (ParseException e) {
			System.out.println("Invalid date");
		}
	}
	
	private static void manga() {
		// e.g.: Manga1,Funny,Author1,1,EP1 is about...
		// e.g.: Manga2,Violence,Author2,3,Ep1 is about...,Ep2 is about...,Ep3 is about...
		
		// name,category,author,numberOfEpisodes,1stEpisodeContent,2ndEpisodeContent,...
		
		// (at least one episode content: name,category,author,numberOfEpisodes,1stEpisodeContent)
		
		RentalSystem rs = RentalSystem.getInstance();
		try {
			String filepathname = "D:\\2021_22_SemA\\CS3343_Software_Engineering_Practice\\Project\\Group21\\mangaDB.txt";
			
			Scanner inFile = new Scanner(new File(filepathname));
			int numberOfManga = 0;
			while (inFile.hasNextLine()) {
				String cmdLine = inFile.nextLine();
				String[] cmdParts = cmdLine.split(",");
				String bookIndex = String.format("%05d", numberOfManga);
				rs.addManga(cmdParts[0], cmdParts[1], cmdParts[2], cmdParts[4]);
				for (int i=1; i<Integer.parseInt(cmdParts[3]); i++) {
					rs.updateManga(bookIndex, cmdParts[i+4]);
				}
				numberOfManga++;
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		}
	}
	
	private static void rentalRecord() {
		// e.g.: test001,00001,2020/12/30,RENT,2,1,3
		// e.g.: test002,00000,2021/11/10,RENT,1,1
		
		// user,mangaID,expireDate,type,numberOfEpisodes,1stRentEpisode,2ndRentEpisode,...
		
		// (at least rent one episode: user,mangaID,expireDate,type,numberOfEpisodes,1stRentEpisode)
		
		RentalSystem rs = RentalSystem.getInstance();
		try {
			String filepathname = "D:\\2021_22_SemA\\CS3343_Software_Engineering_Practice\\Project\\Group21\\RentalRecord.txt";
			
			Scanner inFile = new Scanner(new File(filepathname));
			while (inFile.hasNextLine()) {
				String cmdLine = inFile.nextLine();
				String[] cmdParts = cmdLine.split(",");
				for (User user : users) {
					if (user.getUsername().equals(cmdParts[0])) {
						Manga manga = rs.searchMangaByIndex(cmdParts[1]);
						DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				      	df.setLenient(false);
				      	Date expireDate = df.parse(cmdParts[2]);
				      	ArrayList<Integer> episodesList = new ArrayList<Integer>();
				      	for (int i=0; i<Integer.parseInt(cmdParts[4]); i++) {
				      		episodesList.add(Integer.parseInt(cmdParts[i+5]));
				      	}
				      	if (cmdParts[3].equals("RENT")) {
				      		RentalRecord rr = new RentalRecord(user, manga, episodesList, expireDate, RentalRecordType.RENT);
				      		user.addRentalRecord(rr);
				      	} else if (cmdParts[3].equals("BIRTHDAY_GIFT")) {
				      		RentalRecord rr = new RentalRecord(user, manga, episodesList, expireDate, RentalRecordType.BIRTHDAY_GIFT);
				      		user.addRentalRecord(rr);
				      	}
					}
				}
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (ParseException e) {
			System.out.println("Invalid date");
		}
	}
}
