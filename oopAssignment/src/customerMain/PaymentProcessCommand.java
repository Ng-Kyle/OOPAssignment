package customerMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.FileIO;
import paymentprocessing.Order;
import paymentprocessing.Ticket;

public class PaymentProcessCommand implements Command<Ticket> {
	private Order userOrder;
	private ArrayList<Order> storedOrders;
	private Scanner scan;
	public PaymentProcessCommand(Order userOrder, Scanner scan, ArrayList<Order>storedOrders) {
		this.userOrder = userOrder;
		this.scan = scan;
		this.storedOrders = storedOrders;
	}
	
	public Ticket execute(){
		double finalPrice = userOrder.calculateFinalPrice();
		userOrder.setFinalPrice(finalPrice);
		System.out.println("Total: RM" + finalPrice);
		double userPayment;
			
		while (true) { 
			System.out.print("Enter your payment: RM"); 
			String input = scan.nextLine().trim(); 
			
			if (input.isEmpty() || input == null) { // Check if input is empty
				System.out.println("No payment entered. Funds insufficient. Order was cancelled.");
				return null;
			}
			try {
				userPayment = Double.parseDouble(input); 
				
				if(userPayment<finalPrice) {
					System.out.println("Funds insufficient. Order was cancelled.");
					return null;
				}
				break;
				
			} catch (NumberFormatException nfe) { 
				System.out.println("Invalid input. Please enter a numerical value for payment.");
			}
		}	
	
		try {
			userOrder.reduceStock();
			System.out.println("Order successful! Enjoy your movie.");
			storedOrders.add(userOrder);
			String csvOrders = "";
			for(int i = 0; i < storedOrders.size(); i++) {
				csvOrders += storedOrders.get(i).toCSV() + "\n";
			}
			FileIO.writeFile(csvOrders,"storedData/orders.csv");
			return new Ticket(userOrder);
		}
		catch(InsufficientStockException isfe){
			System.out.println("Insufficient stock. Order was cancelled.");
			}
		return null;
	}

}
