package adminMain;

import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import concession.AddItemCommand;
import concession.ViewItemCommand;
import concession.UpdateItemCommand;
import concession.DeleteItemCommand;

public class ConcessionItemMenuCommand implements Command<Void> {
	private CommandInvoker menuInvoker;
	private Scanner scan;
	public ConcessionItemMenuCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	public Void execute() {
		while(true) {
			System.out.println("\n=============================================================");
			System.out.println("                        ADMIN DASHBOARD                      ");
			System.out.println("=============================================================");
			System.out.println("1. View All Items");
			System.out.println("2. Add New Item");
			System.out.println("3. Update Item");
			System.out.println("4. Delete Item");
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
					menuInvoker.executeCommand(new ViewItemCommand());
					break;
				case 2:
					menuInvoker.executeCommand(new AddItemCommand(scan,menuInvoker));
					break;
				case 3:
					menuInvoker.executeCommand(new UpdateItemCommand(scan,menuInvoker));
					break;
				case 4:
					menuInvoker.executeCommand(new DeleteItemCommand(scan));
					break;
				default:
					System.out.println("Invalid Input, Please try again!");
					break;
				}
			}catch (Exception a) {
				System.out.println("Invalid Input, Please add a number between 0-4");	
			}
		}
	}
}