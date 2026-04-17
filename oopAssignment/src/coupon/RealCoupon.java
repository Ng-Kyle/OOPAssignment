package coupon;
//This is a coupon that actually exists and is used during an order.
public class RealCoupon extends Coupon {

	public RealCoupon(String code, double discountValue, CouponDiscountStrategy strategy) {
		super(code, discountValue, strategy);
	}

	public String toCSV() {
		return super.getCode() + "," + super.getDiscountValue() + "," + super.getStrategy().getClass().getSimpleName();
	}
	
	public static RealCoupon fromCSV(String contents) {
		String[] values = contents.split(",");
		CouponDiscountStrategy storedStrategy = null;
		switch(values[2]) {
		case "FlatDiscountStrategy":
			storedStrategy = new FlatDiscountStrategy();
			break;
		
		case "PercentageDiscountStrategy":
			storedStrategy = new PercentageDiscountStrategy();
		}	
		return new RealCoupon(values[0],Double.parseDouble(values[1]),storedStrategy);
		
	}
}
