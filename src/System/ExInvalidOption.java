package System;

public class ExInvalidOption extends Exception {
	
	public ExInvalidOption() {
		// "\\n" --> wrong
		super("Incorrect option!\\n");
	}
}
