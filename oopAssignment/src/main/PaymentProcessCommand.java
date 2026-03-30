package main;

import java.util.Scanner;

import concession.ConcessionItemStorage;
import paymentprocessing.Order;

public class PaymentProcessCommand implements Command<Void> {
	private Order userOrder;
	private Scanner scan;
	public PaymentProcessCommand(Order userOrder, Scanner scan) {
		this.userOrder = userOrder;
		this.scan = scan;
	}
	
	public Void execute(){
		double finalPrice = userOrder.calculateFinalPrice();
		System.out.println(finalPrice);
		double userPayment = Double.parseDouble(scan.nextLine());
		if(userPayment<finalPrice) {
			System.out.println("Funds insufficient. Order was cancelled.");
		}
		else {
			System.out.println("Order successful! Enjoy your movie.");
			userOrder.reduceStock();
			System.out.println(ConcessionItemStorage.getStockQuantity());
		}
		return null;
	}

}
