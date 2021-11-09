import java.util.*;

public class CmdUpdateManga implements Command {
	
	private RentalSystem rs = RentalSystem.getInstance();
	
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter manga bookIndex: ");
		String bookIndex = scanner.nextLine();
		System.out.print("Please enter new episode's content: ");
		String newContent = scanner.nextLine();
        System.out.println();
        rs.updateManga(bookIndex, newContent);
	}
}