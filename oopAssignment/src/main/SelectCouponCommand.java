package main;
import java.util.Scanner;
import coupon.Coupon;
import coupon.CouponFactory;

public class SelectCouponCommand implements Command<Coupon> {
	private Scanner scan;
	public SelectCouponCommand(Scanner scan) {
		this.scan = scan;
	}
	public Coupon execute() {
		return CouponFactory.selectCoupon(scan.nextLine());
	}
}
