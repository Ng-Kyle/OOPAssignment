package paymentprocessing;

import java.util.ArrayList;

import main.Command;
import showtime.Showtime;
import seatbooking.SeatBooking;

public class ViewOrdersCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	
	public ViewOrdersCommand(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}
	
	public Void execute() { 
		System.out.println("\n=============================================================");
		System.out.println("                        VIEW ORDER LIST                      ");
		System.out.println("=============================================================");
		if (storedOrders == null || storedOrders.isEmpty()) {
			System.out.println("There are no available orders to display.");
		}
		for (Order order : storedOrders) {
			
			Showtime st = order.getShowtime();
			System.out.printf("ORDER ID: %-10d%n",order.getOrderID());
			System.out.printf("MOVIE: %-30s%n", st.toString());
			System.out.printf("SHOWTIME: %-20s | Hall ID: %d%n", st.getTime().toString().replace("T", " "), st.getHall().getHallID());
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("TICKETS");
			for (SeatBooking seat : order.getSeatBooking()) {
				String rawData = seat.toCSV();
			    if (rawData == null || !rawData.contains(":")) {
			        System.out.println(" Invalid Seat Data");
			        continue;
			    }
				String[] seatData = seat.toCSV().split(":");
				if (seatData.length >= 3) {
				System.out.printf(" > Seat %-20s | %-10s | RM%-8.2f%n", (seatData[0]+seatData[1]), seatData[2], seat.getPrice());
				}
			}
			if (!order.getOrderedItems().isEmpty()) {
				System.out.println("\nCONCESSIONS");
				System.out.printf("%-20s | %-10s | %-5s | %-8s%n", "Item Name", "Size", "Qty", "Subtotal");
				for (OrderItem item : order.getOrderedItems()) {
					System.out.printf("%-20s | %-10s | %-5s | RM%-8.2f%n", item.getOrderedItem(), item.getSize(), item.getQuantity(), item.getItemPrice());
			}
		}
			System.out.println("-------------------------------------------------------------------------------------------");
			double total  = order.calculateFinalPrice();
			System.out.printf("%42s: RM%-8.2f%n", "TOTAL PRICE", total);
			System.out.println("-------------------------------------------------------------------------------------------");
		}
		return null;
	}
}
