package coupon;

public class CouponFactory {
	
	public static Coupon selectCoupon(String code) {
		try {
			return CouponCodeStorage.getCouponByCode(code);
		}
		catch(IllegalArgumentException iae) { //Probably ask the user to retype or cancel?
			return new NullCoupon();
		}
	}
}
