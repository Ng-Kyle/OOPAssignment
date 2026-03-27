package oopAssignment;

public class FlatDiscountStrategy implements CouponDiscountStrategy{
	public double applyCoupon(double totalPrice, double value) {
		return totalPrice - value;
	}
}
