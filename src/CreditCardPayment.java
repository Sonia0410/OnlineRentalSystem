import java.util.*;

public class CreditCardPayment implements PaymentMethod {
	
	private static CreditCardPayment instance = new CreditCardPayment();

    public static CreditCardPayment getInstance() {
        return instance;
    }
	
	public boolean pay(User user, int money) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter credit card number: ");
		String cardNum = scanner.nextLine();
		System.out.print("Please enter credit card holder's name: ");
		String holder = scanner.nextLine();
		System.out.print("Please enter credit card password: ");
		String password = scanner.nextLine();
        System.out.println();
        if (cardNum.equals("1234567890123456") && holder.equals("Holder") && password.equals("1234")) {
        	return true;
        } else {
        	System.out.println("\nInvalid Credit Card!\n");
        	return false;
        }
	}
}
