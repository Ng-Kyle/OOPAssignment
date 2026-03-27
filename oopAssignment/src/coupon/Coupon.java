package coupon;

public abstract class Coupon {
	private String code;
	private CouponDiscountStrategy strategy;
	private double discountValue;
	
	public double applyCoupon(double totalPrice) {
		return strategy.applyCoupon(totalPrice, discountValue);
	}
}
