package concession;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;

public class UpdateStockQuantityCommand implements Command<Void> {
	private Scanner scan;
	private CommandInvoker menuInvoker;
	public UpdateStockQuantityCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	public Void execute() {
		while(true) {
			menuInvoker.executeCommand(new ViewStockQuantityCommand());
			menuInvoker.executeCommand(new ViewItemCommand());
			int itemChoice = Integer.parseInt(scan.nextLine());
			if(itemChoice == 0) {
				break;
			}

			ArrayList<ConcessionItem> storedItems = ConcessionItemStorage.getAllItems();
			if(storedItems.get(itemChoice-1) instanceof Combo) {
				System.out.println("You can't modify the stock of combos!");
				continue;
			}
			double stockAmount = Double.parseDouble(scan.nextLine());
			ConcessionItemStorage.addStock(storedItems.get(itemChoice-1).getName(),stockAmount);
			
		}
		return null;
	}
}
