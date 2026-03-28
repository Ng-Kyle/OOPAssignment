package coupon;

public class CouponFactory {
	public static Coupon selectCoupon(String code) {
		if (code.equals("25%OFF")) {
			return new RealCoupon(code,0.25,new PercentageDiscountStrategy());
		}
		if(code.equals("RM5OFF")) {
			return new RealCoupon(code,5, new FlatDiscountStrategy());
		}
		return new NullCoupon();
		
	}
}
