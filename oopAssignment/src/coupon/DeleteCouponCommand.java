package coupon;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;

public class DeleteCouponCommand implements Command<Void> {
	private Scanner scan;
	
	public DeleteCouponCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		ArrayList<RealCoupon> availableCoupons = CouponCodeStorage.getAllCoupons();
		while(true) {
			System.out.println("\n-------------------------DELETE COUPON------------------------");
			if (availableCoupons.isEmpty()) {
				System.out.println("No coupons are available.");
				return null;
			}
			for (int i = 0; i < availableCoupons.size(); i++) {
                RealCoupon c = availableCoupons.get(i);
                System.out.println((i + 1) + ". Code: " + c.getCode() + " | Value: " + c.getDiscountValue());
            }
			System.out.println("Select Showtime to delete(1-" + availableCoupons.size() + ") or 0 to cancel.");
			int couponChoice;
			try {
				couponChoice = Integer.parseInt(scan.nextLine());
					if(couponChoice == 0) {
						break;
					}
					if (couponChoice < 1 || couponChoice > availableCoupons.size()) {
						System.out.println("Invalid Selection! Please pick a number from the list.");
						continue;
					}
					String selectedcode = availableCoupons.get(couponChoice-1).getCode();
					System.out.println("\nAre you sure you want to DELETE " + selectedcode + "." ); //confirmation
					System.out.println("TYPE 'YES' to confirm or 'NO' to cancel.");
					String confirmation = scan.nextLine().trim().toUpperCase();
					if (confirmation.isEmpty() || Character.toUpperCase(confirmation.charAt(0)) != 'Y') {
						System.out.println("Deletion Cancelled");
						continue;
					}
					CouponCodeStorage.removeCoupon(couponChoice);
					System.out.println("Deletion was successful!");
					
				}catch (NumberFormatException a) {
					System.out.println("Invalid Selection! Please enter a number from the list");
					continue;
				}
			
		}
		return null;
	}
}
