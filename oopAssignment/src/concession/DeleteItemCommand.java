package concession;

import java.util.Scanner;

import main.Command;

public class DeleteItemCommand implements Command<Void> {

	private Scanner scan;
	public DeleteItemCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			int itemChoice = Integer.parseInt(scan.nextLine());
			if(itemChoice == 0) {
				break;
			}
			ConcessionItemStorage.removeItem(itemChoice);			
		}
		return null;
	}

}
