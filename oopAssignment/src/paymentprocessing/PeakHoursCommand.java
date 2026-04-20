package paymentprocessing;

import java.util.ArrayList;

import main.Command;

public class PeakHoursCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	public PeakHoursCommand(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}
	public Void execute() {
		return null;
	}
}
