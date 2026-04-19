package customerMain;

import main.Command;
import paymentprocessing.Ticket;
import seatbooking.SeatBooking;
import paymentprocessing.Order;
import paymentprocessing.OrderItem;
import seatbooking.SeatLocation;
import seatbooking.Seat;

public class DisplayReceiptCommand implements Command<Void>{
	private Ticket userReceipt;
	public DisplayReceiptCommand(Ticket userReceipt) {
		this.userReceipt = userReceipt;
	}
	public Void execute() {
		Order order = userReceipt.getOrderDetails();
		
		System.out.println("\n=================================================");
		System.out.println("                      RECEIPT                ");
		System.out.println("=================================================");
		System.out.printf("Order ID: #%d%n", order.getOrderID());
		System.out.println("-------------------------------------------------");
		
		System.out.println("SEATS:");
		for (SeatBooking seatBooking : order.getSeatBooking()) {
			Seat seat = seatBooking.getSeat();
			SeatLocation loc = seat.getSeatLocation();
			System.out.printf("Seat: %-10s", loc.toString());
			System.out.printf("Price: RM%.2f%n", seatBooking.getPrice());
		}
		
		System.out.println("\nConcession Items:");
		for (OrderItem item : order.getOrderedItems()) {
			System.out.printf("%-15s (%-6s) x%d RM%.2f%n",
			item.getOrderedItem(),
			item.getSize(),
			item.getQuantity(),
			item.getItemPrice());
		}
		
		System.out.println("-------------------------------------------------");
		System.out.printf("TOTAL: RM%.2f%n", order.calculateFinalPrice());
		System.out.println("-------------------------------------------------");
		return null;
	}
}
