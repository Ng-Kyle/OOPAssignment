package concession;

import java.util.ArrayList;
import java.util.Map;
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

			try {
				System.out.println("-----------UPDATE STOCK QUANTITY-------------");
				menuInvoker.executeCommand(new ViewStockQuantityCommand());
				menuInvoker.executeCommand(new ViewItemCommand());
				
				System.out.println("Please enter the number of the selected item.");
				System.out.println("Enter 0 to exit.");
			
				int itemChoice = Integer.parseInt(scan.nextLine());
				if(itemChoice == 0) {
					break;
				}

			ArrayList<ConcessionItem> storedItems = ConcessionItemStorage.getAllItems();
			
						
			if(storedItems.get(itemChoice-1) instanceof Combo) {
				System.out.println("You can't modify the stock of combos!");
				continue;
			}
			
		
			
			if (itemChoice < 1 || itemChoice > storedItems.size()) {
		        System.out.println("Invalid selection. Please choose a number from the list.");
		        continue;
		    }
			
			
			System.out.println("Please enter the quantity amount to add:");
			double stockAmount = Double.parseDouble(scan.nextLine());
			ConcessionItemStorage.addStock(storedItems.get(itemChoice-1).getName(),stockAmount);
			System.out.println("------  Update successful!  ------");
			
			System.out.print("Would you like to update another item? (y/n): ");
            String choice = scan.nextLine().trim().toLowerCase();

            if (choice.equals("n")) {
                System.out.println("Returning to main menu...");
                break;
            } else if (!choice.equals("y")) {
                System.out.println("Unknown input. Returning to menu by default.");
                break;
            }
				
				}catch(NumberFormatException e) {
					System.out.println("Error: Please enter a valid numerical value.");
	        }
				
		}return null;
	}
	
}
