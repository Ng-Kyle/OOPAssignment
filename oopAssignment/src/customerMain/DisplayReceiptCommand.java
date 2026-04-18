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
			String seatDesc = String.format("%s (%s)", loc.toString(), seat.getSeatType());
			System.out.printf("Seat: %-22s Price: RM%.2f%n", seatDesc, seatBooking.getPrice() );
		}
		
		System.out.println("\nConcession Items:");
		for (OrderItem item : order.getOrderedItems()) {
			System.out.printf("%-1s (%-6s) x%-10d Price: RM%.2f%n",
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
