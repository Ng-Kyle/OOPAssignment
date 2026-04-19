package paymentprocessing;

import java.util.ArrayList;

import main.Command;

public class ViewOrdersCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	
	public ViewOrdersCommand(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}
	
	public Void execute() { //TODO: ADD ORDER MENU
		return null;
	}
}
