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
			System.out.println("\n-------------------------ADD NEW COUPON------------------------");
			System.out.println("Enter new coupon CODE: ");
			String userCode = scan.nextLine().trim().toUpperCase();
			
			for (RealCoupon existing : CouponCodeStorage.getAllCoupons()) {
				if (existing.getCode().equalsIgnoreCase(userCode)) {
					System.out.println("Error! Coupon already exists.");
					return null;
				}
			}
			
			try {
				System.out.println("Select Discount Type:");
		        System.out.println("1. Percentage (%)");
		        System.out.println("2. Flat Rate (RM)");
		        System.out.print("Choice: ");
		        int userChoice = Integer.parseInt(scan.nextLine());
		        CouponDiscountStrategy discountStrategy;
				String unit;
		        
				switch(userChoice) {//TODO: Ensure that they have to select a proper discount strategy or cancel
				case 1:
					discountStrategy = new PercentageDiscountStrategy();
					unit = "Percentage (e.g. 25%)";
					System.out.println(unit);
					break;
				case 2:
					discountStrategy = new FlatDiscountStrategy();
					unit = "Flat (e.g. 5.50 for RM5.50";
					System.out.println(unit);
					break;
				default:
					System.out.println("Invalid Selection. Defaulting back to Percentage (%).");
					discountStrategy = new PercentageDiscountStrategy();
					break;
				}
				System.out.println("Enter Discount Value: ");
				double discountValue = Double.parseDouble(scan.nextLine());
				CouponCodeStorage.addCoupon(userCode, discountValue, discountStrategy);
				System.out.println("Coupon [" + userCode +"] added successfully!");
				
			}catch(NumberFormatException e) {
				System.out.println("Input Error: Please enter valid numbers.");
			}
			
			System.out.print("\nAdd another coupon? (Y/N): ");
			String menuInput = scan.nextLine().trim();
			if (menuInput.isEmpty() || Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break; 
			}
		}	
		return null;
	}
}
	

