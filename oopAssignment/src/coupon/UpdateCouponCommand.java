package coupon;

import java.util.ArrayList;
import java.util.Scanner;


import main.Command;

public class UpdateCouponCommand implements Command<Void> {
	private Scanner scan;
	
	public UpdateCouponCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		ArrayList<RealCoupon> availableCoupons = CouponCodeStorage.getAllCoupons();
		while(true) {
			int userChoice = Integer.parseInt(scan.nextLine());
			if(userChoice == 0) {
				break;
			}
			RealCoupon chosenCoupon = availableCoupons.get(userChoice-1);
			while(true) {
				int updateChoice = Integer.parseInt(scan.nextLine());
				if(updateChoice == 0) {
					break;
				}
				
				
				switch(updateChoice) {
				case 1:
					String code = scan.nextLine();
					chosenCoupon.setCode(code);;
					CouponCodeStorage.toCSV();
					break;
				case 2:
					CouponDiscountStrategy discountStrategy = new NoDiscountStrategy();
					int discountChoice = Integer.parseInt(scan.nextLine());
					switch(discountChoice) { //TODO: Ensure that they have to select a proper discount strategy or cancel
					case 1:
						discountStrategy = new PercentageDiscountStrategy();
						break;
					case 2:
						discountStrategy = new FlatDiscountStrategy();
						break;
					default:
					}
					double discountValue = Double.parseDouble(scan.nextLine());
					chosenCoupon.setDiscountValue(discountValue);
					chosenCoupon.setStrategy(discountStrategy);
					CouponCodeStorage.toCSV();
				}
			}
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}

}
