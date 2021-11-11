package System;

import java.util.*;

public class MasterCardPayment implements PaymentMethod {
	
	private static MasterCardPayment instance = new MasterCardPayment();

    public static MasterCardPayment getInstance() {
        return instance;
    }
	
	public boolean pay(User user, int money) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter master card number: ");
		String cardNum = scanner.nextLine();
        System.out.println();
        if (cardNum.equals("6543210987654321")) {
        	return true;
        } else {
        	System.out.println("\nInvalid master card!\n");
        	return false;
        }
	}
}