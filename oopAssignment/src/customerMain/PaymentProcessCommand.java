package customerMain;

import java.util.ArrayList;
import java.util.Scanner;

import concession.ConcessionItemStorage;
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
		System.out.println(finalPrice);
		double userPayment = Double.parseDouble(scan.nextLine());
		if(userPayment<finalPrice) {
			System.out.println("Funds insufficient. Order was cancelled.");
			return null;
		}
		try {
			userOrder.reduceStock();
			System.out.println("Order successful! Enjoy your movie.");
			System.out.println(ConcessionItemStorage.getStockQuantity());
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
