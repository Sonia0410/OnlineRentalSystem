import java.util.*;

public class CmdAddManga implements Command {
	
	private RentalSystem rs = RentalSystem.getInstance();
	
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter manga name: ");
		String name = scanner.nextLine();
		System.out.print("Please enter manga category: ");
		String category = scanner.nextLine();
		System.out.print("Please enter manga author: ");
		String author = scanner.nextLine();
		System.out.print("Please enter manga first episode content: ");
		String firstEpisodeContent = scanner.nextLine();
        System.out.println();
        rs.addManga(name, category, author, firstEpisodeContent);
	}
}