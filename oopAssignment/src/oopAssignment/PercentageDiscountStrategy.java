package oopAssignment;
//For coupons that apply discounts like -20% off
public class PercentageDiscountStrategy implements CouponDiscountStrategy{
	public double applyCoupon(double totalPrice, double value){
		return totalPrice * (1-value);
	}
}
