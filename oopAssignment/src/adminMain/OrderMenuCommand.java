package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import paymentprocessing.Order;
import paymentprocessing.ViewOrdersCommand;
import paymentprocessing.ValidateQRTicketCommand;

public class OrderMenuCommand implements Command<Void> {
	private CommandInvoker menuInvoker;
	private Scanner scan;
	private ArrayList<Order> storedOrders;
	public OrderMenuCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Order> storedOrders) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
		this.storedOrders = storedOrders;
	}
	public Void execute() {
		while(true) {
			System.out.println("\n=============================================================");
			System.out.println("                        ADMIN DASHBOARD                      ");
			System.out.println("=============================================================");
			System.out.println("1. View Orders");
			System.out.println("2. Validate QR Ticket");
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
					menuInvoker.executeCommand(new ViewOrdersCommand(storedOrders));
					break;
				case 2:
					menuInvoker.executeCommand(new ValidateQRTicketCommand(scan,storedOrders));
					break;
				
				}
			}
			catch (Exception a) {
				a.printStackTrace();
			}
		}
	}
}
