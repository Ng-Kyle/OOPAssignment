package coupon;

public interface CouponDiscountStrategy {
	public double applyCoupon(double totalPrice, double discountValue);
}
