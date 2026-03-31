package seatbooking;
import java.util.HashMap;
import java.util.Map;

public class SeatBooking {
	private Seat seat;
	private double price;
	private static Map<SeatType, Double > seatPrices = new HashMap<>();
	static { //Replace this with json file loading later on
		seatPrices.put(SeatType.STANDARD, 20.0);
		seatPrices.put(SeatType.VIP, 30.0);
	}
	public double getPrice() {
		return price;
	}
	public SeatBooking(){
		seat = null;
		price = 0.0;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public Seat getSeat(){
		return seat;
	}
	public void setSeat(Seat seat){
		this.seat = seat; 
	}
	
	public SeatBooking(Seat seat) {
		this.seat = seat;
		price = seatPrices.get(seat.getSeatType());
	}
}
