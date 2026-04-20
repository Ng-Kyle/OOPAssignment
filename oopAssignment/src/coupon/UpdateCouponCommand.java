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
			System.out.println("\n-------------------------UPDATE COUPON------------------------");
			if (availableCoupons.isEmpty()) {
				System.out.println("No coupons are available.");
				return null;
			}
			for (int i = 0; i < availableCoupons.size(); i++) {
                RealCoupon c = availableCoupons.get(i);
                System.out.println((i + 1) + ". Code: " + c.getCode() + " | Value: " + c.getDiscountValue());
            }
			int couponChoice;
			try {
				couponChoice = Integer.parseInt(scan.nextLine());
					if(couponChoice == 0) {
						return null;
					}
					if (couponChoice < 1 || couponChoice > availableCoupons.size()) throw new Exception();
				}catch (Exception a) {
					System.out.println("Invalid Selection! Please enter a number from the list");
					continue;
				}
			
			RealCoupon chosenCoupon = availableCoupons.get(couponChoice-1);
			while(true) {
				System.out.println("\n**Updating: " + chosenCoupon.getCode() + "**");
                System.out.println("1. Change Code: " + chosenCoupon.getCode());
                System.out.println("2. Change Value: " + chosenCoupon.getDiscountValue());
                System.out.println("3. Change Type (Strategy)");
                System.out.println("4. Save and Return");
                System.out.print("Choice: ");
                
                int updateChoice; 
				try {
					updateChoice= Integer.parseInt(scan.nextLine());
				}catch (Exception e) {
					System.out.println("Invalid Input! Please enter a number.");
					continue;
				}
				if (updateChoice == 4) {
					CouponCodeStorage.toCSV();
					System.out.println("Changed Save Successfully 😊");
					break;
				}
				
				
				switch(updateChoice) {
				case 1:
					System.out.println("Enter a new code: ");
					String code = scan.nextLine().trim().toUpperCase();
					chosenCoupon.setCode(code);;
					System.out.println("Code Updated!");
					break;
				case 2:
					try {
						System.out.print("Enter New Value");
						double newVal = Double.parseDouble(scan.nextLine());
                        chosenCoupon.setDiscountValue(newVal);
                        System.out.println("Value updated!");
					}catch (Exception e) {
						System.out.println("Invalid numeric Value");
					}
					break;
				case 3:
					System.out.println("Select Discount Type:");
			        System.out.println("1. Percentage (%)");
			        System.out.println("2. Flat Rate (RM)");
			        System.out.print("Choice: ");
			        try {
			        	int userChoice = Integer.parseInt(scan.nextLine());
			        	CouponDiscountStrategy discountStrategy;
			        	String unit;
			        	
			        	switch(userChoice) {//TODO: Ensure that they have to select a proper discount strategy or cancel
						case 1:
							discountStrategy = new PercentageDiscountStrategy();
							unit = "Percentage (e.g. 25%)";
							System.out.println("Strategy changed to Percentage.");
							break;
						case 2:
							discountStrategy = new FlatDiscountStrategy();
							unit = "Flat (e.g. 5.50 for RM5.50";
							System.out.println("Strategy changed to Flat.");
							break;
						default:
							System.out.println("Invalid Selection. Defaulting back to Percentage (%).");
							discountStrategy = new PercentageDiscountStrategy();
							continue;
						}
			        	System.out.println("Enter Discount Value: ");
						double discountValue = Double.parseDouble(scan.nextLine());
						if(discountValue > 1 && discountStrategy instanceof PercentageDiscountStrategy) {
							throw new NumberFormatException();
						}
						chosenCoupon.setStrategy(discountStrategy);
						chosenCoupon.setDiscountValue(discountValue);
						System.out.println("Coupon updated successfully!");
			        }catch (NumberFormatException e) {
			        	System.out.println("Input Error: Please enter valid numbers.");
			        }
			        break;
			      default:
			    	  System.out.println("Invalid Choice");
				}
			}
			return null;
		}
	}
}