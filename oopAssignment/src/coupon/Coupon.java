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
	public void setStrategy(CouponDiscountStrategy strategy){
		this.strategy = strategy;
	}
	public void setDiscountValue(double discountValue){
		this.discountValue = discountValue;
	}
	
	public String toString(){
		return String.format("Code: %s \nDiscount Value: %.2f \nStrategy:%s",
				this.code,this.discountValue,this.strategy.getClass().getSimpleName());
	}

}
