package concession;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;

public class UpdateStockQuantityCommand implements Command<Void> {
	private Scanner scan;
	public UpdateStockQuantityCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			String input = scan.nextLine().trim();
			if (input.isEmpty()) {
	            System.out.println("Input cannot be empty.");
	            continue;
	        }
			
			
			int itemChoice = Integer.parseInt(scan.nextLine()) - 1;
			ArrayList<ConcessionItem> storedItems = ConcessionItemStorage.getAllItems();
			if(storedItems.get(itemChoice) instanceof Combo) {
				System.out.println("You can't modify the stock of combos!");
				continue;
			}
			double stockAmount = Double.parseDouble(scan.nextLine());
			ConcessionItemStorage.addStock(storedItems.get(itemChoice).getName(),stockAmount);
			if(itemChoice == 0) {
				break;
			}
		}
		return null;
	}
}
