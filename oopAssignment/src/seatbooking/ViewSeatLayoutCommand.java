package seatbooking;

import java.util.ArrayList;
import java.util.Map;

import main.Command;
import paymentprocessing.Order;
import showtime.Showtime;

public class ViewSeatLayoutCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	private Showtime chosenShowtime;
	
	public ViewSeatLayoutCommand(ArrayList<Order> storedOrders, Showtime chosenShowtime) {
		this.storedOrders = storedOrders;
		this.chosenShowtime = chosenShowtime;
	}
	public Void execute() {
		ArrayList<Seat> takenSeats = new ArrayList<>();
		for(int i = 0; i < storedOrders.size(); i++) {
			if(storedOrders.get(i).getShowtime().getShowtimeID() == chosenShowtime.getShowtimeID()) {
				ArrayList<SeatBooking> orderSeatBookings = storedOrders.get(i).getSeatBooking();
				for(int j = 0; j < orderSeatBookings.size(); j++) {
					takenSeats.add(orderSeatBookings.get(j).getSeat());
				}
			}
		}
		Seat[] showtimeSeats = chosenShowtime.getHall().getSeats();
		for(int i = 0; i<showtimeSeats.length; i++) {
			SeatLocation seatLocation = showtimeSeats[i].getSeatLocation();
			boolean seatAvailable = true;
			for(int j = 0; j < takenSeats.size(); j++) {
				if(seatLocation.equals(takenSeats.get(j).getSeatLocation())) {
					seatAvailable = false;
					break;
				}
			}
			if(i != 0 && seatLocation.getRow() != showtimeSeats[i-1].getSeatLocation().getRow()) {
				System.out.print("\n");
			}
			if(seatAvailable == true) {
				System.out.print(seatLocation.toString() + "   ");
			}
			else {
				System.out.print("X    ");
			}					
			
		}
		int vipRowStart = chosenShowtime.getHall().getRows()/3;
		int vipRowEnd =  2 * chosenShowtime.getHall().getRows()/3;
		char vipRowCodeStart = (char) ('A' + vipRowStart);
		char vipRowCodeEnd = (char) ('A' + vipRowEnd);
		System.out.println("\nRows " + vipRowCodeStart + " - " + vipRowCodeEnd + " are VIP Seats");
		Map<SeatType,Double> seatPrices = SeatBooking.getSeatPrices();
		System.out.printf("Standard Seat: RM %.2f VIP Seat: RM %.2f", seatPrices.get(SeatType.STANDARD),seatPrices.get(SeatType.VIP));
		return null;
	}
}


