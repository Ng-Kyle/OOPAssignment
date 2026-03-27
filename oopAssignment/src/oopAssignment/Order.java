package oopAssignment;

public class Order {
	private Showtime showtime;
	private ConcessionItem[] orderedConcessionItems;
	private Coupon coupon;
	
	public void calculateTotalPrice() {
		double finalPrice = 0;
		for(int i = 0; i < orderedConcessionItems.length; i++) {
			finalPrice += orderedConcessionItems[i].getPrice();
		}
		finalPrice = coupon.applyCoupon(finalPrice);
	}
	
}
