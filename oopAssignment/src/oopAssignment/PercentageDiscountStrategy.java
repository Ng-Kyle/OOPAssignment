package oopAssignment;

public class PercentageDiscountStrategy implements CouponDiscountStrategy{
	public double applyCoupon(double totalPrice, double value){
		return totalPrice * (1-value);
	}
}
