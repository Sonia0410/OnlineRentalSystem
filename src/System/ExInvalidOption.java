package System;

public class ExInvalidOption extends Exception {
	
	public ExInvalidOption() {
		// fixed ("\\n" --> "\n")
		super("Incorrect option!\n");
	}
}
