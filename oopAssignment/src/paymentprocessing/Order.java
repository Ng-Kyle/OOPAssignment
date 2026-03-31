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
	private double finalPrice;
	
	public Order(Showtime showtime, ArrayList<ConcessionItem> orderedConcessionItems, Coupon coupon, ArrayList<SeatBooking> seatBookings) {
		this.showtime = showtime;
		this.orderedConcessionItems = orderedConcessionItems;
		this.coupon = coupon;
		this.seatBookings = seatBookings;
	}
	
	public double calculateFinalPrice() {
		finalPrice = 0;
		for(int i = 0; i < seatBookings.size(); i++) {
			finalPrice += seatBookings.get(i).getPrice();
		}
		for(int i = 0; i < orderedConcessionItems.size(); i++) {
			finalPrice += orderedConcessionItems.get(i).calculatePrice();
		}
		finalPrice = coupon.applyCoupon(finalPrice);
		return finalPrice;
	}
	
	public void reduceStock() {
		for(ConcessionItem concessionItem:orderedConcessionItems) {
			concessionItem.reduceStock();
		}
	}
	
	public String toString() {
		return showtime.toString();//TODO: Nigel/Emily make a CSV format for this
	}
	
}
