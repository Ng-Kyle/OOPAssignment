package paymentprocessing;

import java.util.ArrayList;

import main.Command;

public class BestSellingMovieCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	public BestSellingMovieCommand(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}
	public Void execute() {
		return null;
	}
}
