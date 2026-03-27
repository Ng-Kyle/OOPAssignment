package oopAssignment;
//Only used by the NoCoupon Class
public class NoDiscountStrategy implements CouponDiscountStrategy {

	@Override
	public double applyCoupon(double totalPrice, double value) {
		return totalPrice;
	}

}
