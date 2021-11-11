package System;

public class ExExistUser extends Exception {
	
	public ExExistUser() {
		super("This username has already been used.\n");
	}
}
