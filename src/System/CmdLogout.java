package System;

import System.User;

public class CmdLogout implements Command {
	
	private User user;
	
	public CmdLogout(User user) {
		this.user = user;
	}
	
	public void execute() {
        user.logout();
	}
}
