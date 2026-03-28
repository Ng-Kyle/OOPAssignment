package seatbooking;
import java.util.HashMap;
import java.util.Map;

public class SeatBooking {
	private Seat seat;
	private TicketType ticketType;
	private double price;
	private static Map<TicketType, Double > ticketPrices = new HashMap<>();
	static { //Replace this with json file loading later on
		ticketPrices.put(TicketType.ADULT, 21.0);
		ticketPrices.put(TicketType.CHILD, 11.0);
		ticketPrices.put(TicketType.OKU, 11.0);
		ticketPrices.put(TicketType.SENIOR, 11.0);
	}
	public double getPrice() {
		return price;
	}
	public SeatBooking(Seat seat, TicketType ticketType) {
		this.seat = seat;
		price = ticketPrices.get(ticketType);
	}
}
