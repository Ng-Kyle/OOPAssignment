package paymentprocessing;

import java.util.ArrayList;

import concession.ConcessionItem;
import coupon.Coupon;
import seatbooking.SeatBooking;
import seatbooking.Showtime;

public class Order {
	private Showtime showtime;
	private ArrayList<ConcessionItem> orderedConcessionItems = new ArrayList<>();
	private Coupon coupon;
	private ArrayList<SeatBooking> seatBookings = new ArrayList<>();
	
	
	public double calculateFinalPrice() {
		double finalPrice = 0;
		for(int i = 0; i < seatBookings.size(); i++) {
			finalPrice += seatBookings.get(i).getPrice();
		}
		for(int i = 0; i < orderedConcessionItems.size(); i++) {
			finalPrice += orderedConcessionItems.get(i).getPrice();
		}
		finalPrice = coupon.applyCoupon(finalPrice);
		return finalPrice;
	}
	
	
}
