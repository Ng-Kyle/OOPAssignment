package main;

import java.util.Scanner;

import concession.ConcessionItemStorage;
import paymentprocessing.Order;

import paymentprocessing.Ticket;


public class PaymentProcessCommand implements Command<Ticket> {
	private Order userOrder;
	private Scanner scan;
	public PaymentProcessCommand(Order userOrder, Scanner scan) {
		this.userOrder = userOrder;
		this.scan = scan;
	}
	
	public Ticket execute(){
		double finalPrice = userOrder.calculateFinalPrice();
		System.out.println(finalPrice);
		double userPayment = Double.parseDouble(scan.nextLine());
		if(userPayment<finalPrice) {
			System.out.println("Funds insufficient. Order was cancelled.");
		}
		else {
			try {
				userOrder.reduceStock();
				System.out.println("Order successful! Enjoy your movie.");
				System.out.println(ConcessionItemStorage.getStockQuantity());
				return new Ticket(userOrder);
			}
			catch(InsufficientStockException isfe){
				System.out.println("Insufficient stock. Order was cancelled.");
			}

		}
		return null;
	}

}
