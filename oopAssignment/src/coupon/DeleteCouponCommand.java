package coupon;

import java.util.Scanner;

import main.Command;

public class DeleteCouponCommand implements Command<Void> {
	private Scanner scan;
	
	public DeleteCouponCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			int couponChoice = Integer.parseInt(scan.nextLine());
			if(couponChoice == 0) {
				break;
			}
			CouponCodeStorage.removeCoupon(couponChoice);
		}
		return null;
	}
}
