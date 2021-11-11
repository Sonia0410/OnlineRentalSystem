package System;

import java.util.*;

public class Payment {
	
	private PaymentMethod paymentMethod;
	private static Payment instance = new Payment();

    public static Payment getInstance() {
        return instance;
    }
	
	public boolean pay(User user, int amount) {
		while (true) {
			try {
				System.out.println("Please pay $" + amount);
				System.out.println("Payment Method Option:");
		    	System.out.println("1: Visa Card Payment");
		    	System.out.println("2: Master Card Payment");
				Scanner scanner = new Scanner(System.in);
				System.out.print("\nPlease enter you option: ");
				String option = scanner.nextLine();
				System.out.println();
				if (option.equals("1")) {
					paymentMethod = VisaCardPayment.getInstance();
				} else if (option.equals("2")) {
					paymentMethod = MasterCardPayment.getInstance();
				} else {
					throw new ExInvalidOption();
				}
				if (paymentMethod.pay(user, amount)) {
					return true;
				} else {
					return false;
				}
			} catch (ExInvalidOption e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
}
