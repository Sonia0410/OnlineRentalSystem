package System;

import System.User;

public class CmdBuyCoins implements Command {
	
	private Payment payment = Payment.getInstance();
	private User user;
	private int coins;
	private int money;
	
	public CmdBuyCoins(User user, int coins, int money) {
		this.user = user;
		this.coins = coins;
		this.money = money;
	}
	
	public void execute() {
		if (payment.pay(user, money)) {
			user.purchaseCoin(coins);
			System.out.println("\nSuccessfully purchased " + coins + " coins.");
		} else {
			System.out.println("Cannot purchse the coins.");
		}
		System.out.println();
	}
}
