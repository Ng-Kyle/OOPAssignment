package coupon;

import java.util.Scanner;

import main.Command;

public class AddCouponCommand implements Command<Void> {
	private Scanner scan;
	
	public AddCouponCommand(Scanner scan) {
		this.scan = scan;
	}
	
	public Void execute() {
		while(true) {
			String userCode = scan.nextLine().trim();
			int userChoice = Integer.parseInt(scan.nextLine());
			CouponDiscountStrategy discountStrategy = new NoDiscountStrategy();
			switch(userChoice) {//TODO: Ensure that they have to select a proper discount strategy or cancel
			case 1:
				discountStrategy = new PercentageDiscountStrategy();
				break;
			case 2:
				discountStrategy = new FlatDiscountStrategy();
				break;
			default:
			}
			double discountValue = Double.parseDouble(scan.nextLine());
			CouponCodeStorage.addCoupon(userCode, discountValue, discountStrategy);
			System.out.println("Add new?");
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
