package coupon;

public class CouponFactory {
	
	public static Coupon selectCoupon(String code) {
		return CouponCodeStorage.getCouponByCode(code);
	}
}
