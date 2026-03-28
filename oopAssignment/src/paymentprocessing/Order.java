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
	
	public Order(Showtime showtime, ArrayList<ConcessionItem> orderedConcessionItems, Coupon coupon, ArrayList<SeatBooking> seatBookings) {
		this.showtime = showtime;
		this.orderedConcessionItems = orderedConcessionItems;
		this.coupon = coupon;
		this.seatBookings = seatBookings;
	}
	
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
