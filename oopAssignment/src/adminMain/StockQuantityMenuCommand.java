package adminMain;

import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import concession.ViewStockQuantityCommand;
import concession.UpdateStockQuantityCommand;

public class StockQuantityMenuCommand implements Command<Void> {
	private CommandInvoker menuInvoker;
	private Scanner scan;
	public StockQuantityMenuCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	public Void execute() {
		while(true) {
			System.out.println("\n=============================================================");
			System.out.println("                        ADMIN DASHBOARD                      ");
			System.out.println("=============================================================");
			System.out.println("1. View Stock Quantity");
			System.out.println("2. Update Stock Quantity");
			System.out.println("0. Exit");
			System.out.println("-------------------------------------------------------------");
			System.out.println("Select an option: ");
			
			String input = scan.nextLine().trim();
			if (input.isEmpty()) {
	            System.out.println("Input cannot be empty.");
	            continue;
	        }
			
			try {
				int menuChoice = Integer.parseInt(input);
				if(menuChoice == 0) {
					return null;
				}
				switch(menuChoice) {
				case 1:
					menuInvoker.executeCommand(new ViewStockQuantityCommand());
					break;
				case 2:
					menuInvoker.executeCommand(new UpdateStockQuantityCommand(scan));
					break;
				default:
					System.out.println("Invalid choice! Please enter the correct number.");
					break;
				}
			} catch (Exception a) {
				System.out.println("Invalid Input, Please add a number between 0-2");
			}
		}
	}
}
