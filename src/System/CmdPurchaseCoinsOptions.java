package System;

import java.util.*;

public class CmdPurchaseCoinsOptions implements Command {
	
	private User user;
	
//	Code Refactoring: Substitute Algorithm
	private String[][] coinsOption = {{"100", "6"}, {"200", "12"}, {"500", "30"}, {"1000", "60"}};
	
	public CmdPurchaseCoinsOptions(User user) {
		this.user = user;
	}
	
	public void execute() {
		try {
			System.out.println("Option:");
//			Code Refactoring: Substitute Algorithm
			for (int i=0; i<coinsOption.length; i++) {
				System.out.println((i+1) + ": " + coinsOption[i][0] + " coins ($" + coinsOption[i][1] + ")");
			}
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nPlease enter you option: ");
			String option = scanner.nextLine();
			System.out.println();
			
			int optionInt;
			try {
				optionInt = Integer.parseInt(option);
			} catch (NumberFormatException e) {
				throw new ExInvalidOption();
			}
			
			if (optionInt > 0 && optionInt <= coinsOption.length) {
				int coins = Integer.parseInt(coinsOption[optionInt-1][0]);
				int money = Integer.parseInt(coinsOption[optionInt-1][1]);
				(new CmdBuyCoins(user, coins, money)).execute();
			} else {
				throw new ExInvalidOption();
			}
		} catch (ExInvalidOption e) {
			System.out.println(e.getMessage());
		}
	}
}