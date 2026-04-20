package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import paymentprocessing.Order;
import paymentprocessing.BestSellingMovieCommand;
import paymentprocessing.PeakHoursCommand;

public class ReportGenerationMenu implements Command<Void> {
	private Scanner scan;
	private CommandInvoker menuInvoker;
	private ArrayList<Order> storedOrders;
	public ReportGenerationMenu(Scanner scan, CommandInvoker menuInvoker, ArrayList<Order> storedOrders){
		this.menuInvoker = menuInvoker;
		this.scan = scan;
		this.storedOrders = storedOrders;
	}
	public Void execute() {
		while (true) {
			System.out.println("\n=============================================================");
			System.out.println("                        ADMIN DASHBOARD                      ");
			System.out.println("=============================================================");
			System.out.println("1. Best Selling Movies Report");
			System.out.println("2. Peak Hours Report");
			System.out.println("0. Exit");
			System.out.println("-------------------------------------------------------------");
			System.out.println("Select an option: ");
			String input = scan.nextLine().trim();
			
			try {
				int menuChoice = Integer.parseInt(input);
				if(menuChoice == 0) {
					return null;
				}
				switch(menuChoice) {
				case 1:
					menuInvoker.executeCommand(new BestSellingMovieCommand(storedOrders));
					break;
				case 2:
					menuInvoker.executeCommand(new PeakHoursCommand(storedOrders));
					break;
				default:
					System.out.println("Invalid option");
						continue;
				}
			}
			catch(Exception e) {
				System.out.println("Invalid input,Please try again");
				continue;
			}
		}
	}
}
