package System;

import java.util.*;

public class VisaCardPayment implements PaymentMethod {
	
	private static VisaCardPayment instance = new VisaCardPayment();

    public static VisaCardPayment getInstance() {
        return instance;
    }
	
	public boolean pay(User user, int money) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter visa card number: ");
		String cardNum = scanner.nextLine();
        System.out.println();
        if (cardNum.equals("1234567890123456")) {
        	return true;
        } else {
        	System.out.println("\nInvalid visa card!\n");
        	return false;
        }
	}
}
