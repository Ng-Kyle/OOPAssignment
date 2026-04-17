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
	
	public Coupon(){
		code = "";
		discountValue = 0.0;
		
	}
	public String getCode(){
		return code;
	}
	public CouponDiscountStrategy getStrategy(){
		return strategy;
	}
	public double getDiscountValue(){
		return discountValue;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public void setstrategy(CouponDiscountStrategy strategy){
		this.strategy = strategy;
	}
	public void setDiscountValue(double discountValue){
		this.discountValue = discountValue;
	}
	
	public String toString(){
		return String.format("Code: \n Discount Value: %.2f \n Strategy:%.2f",code,discountValue,strategy);
	}

}
