package coupon;
//For coupons that apply discounts like RM2 off
public class FlatDiscountStrategy implements CouponDiscountStrategy{
	public double applyCoupon(double totalPrice, double value) {
		return totalPrice - value;
	}
}
