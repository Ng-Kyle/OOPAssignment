package coupon;

public abstract class Coupon {
	private String code;
	private CouponDiscountStrategy strategy;
	private double discountValue;
	
	public Coupon(String code, double discountValue, CouponDiscountStrategy strategy) {
		this.code = code;
		this.discountValue = discountValue;
		this.strategy = strategy;
	}
	public double applyCoupon(double totalPrice) {
		return strategy.applyCoupon(totalPrice, discountValue);
	}
}
