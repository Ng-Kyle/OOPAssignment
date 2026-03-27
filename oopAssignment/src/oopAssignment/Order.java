package oopAssignment;

public class Order {
	private Showtime showtime;
	private ConcessionItem[] orderedConcessionItems = new ConcessionItem[0];
	private Coupon coupon;
	private SeatBooking[] seatBookings = new SeatBooking[0];
	
	public double calculateFinalPrice() {
		double finalPrice = 0;
		for(int i = 0; i < seatBookings.length; i++) {
			finalPrice += seatBookings[i].getPrice();
		}
		for(int i = 0; i < orderedConcessionItems.length; i++) {
			finalPrice += orderedConcessionItems[i].getPrice();
		}
		finalPrice = coupon.applyCoupon(finalPrice);
		return finalPrice;
	}
	
}
