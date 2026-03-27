package oopAssignment;

public class NoDiscountStrategy implements CouponDiscountStrategy {

	@Override
	public double applyCoupon(double totalPrice, double value) {
		return totalPrice;
	}

}
