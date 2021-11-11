package System;

import java.util.*;

public class CmdPurchaseCoinsOptions implements Command {
	
	private User user;
	
	public CmdPurchaseCoinsOptions(User user) {
		this.user = user;
	}
	
	public void execute() {
		try {
			System.out.println("Option:");
	    	System.out.println("1: 100 coins ($6)");
	    	System.out.println("2: 200 coins ($12)");
	    	System.out.println("3: 500 coins ($30)");
	    	System.out.println("4: 1000 coins ($60)");
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nPlease enter you option: ");
			String option = scanner.nextLine();
			System.out.println();
			int coins = 0;
			int money = 0;
			if (option.equals("1")) {
				coins = 100;
				money = 6;
			} else if (option.equals("2")) {
				coins = 200;
				money = 12;
			} else if (option.equals("3")) {
				coins = 500;
				money = 30;
			} else if (option.equals("4")) {
				coins = 1000;
				money = 60;
			} else {
				throw new ExInvalidOption();
			}
			(new CmdBuyCoins(user, coins, money)).execute();
		} catch (ExInvalidOption e) {
			System.out.println(e.getMessage());
		}
	}
}