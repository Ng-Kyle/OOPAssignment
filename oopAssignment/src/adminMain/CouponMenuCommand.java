package adminMain;

import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import coupon.ViewCouponCommand;
import coupon.AddCouponCommand;
import coupon.UpdateCouponCommand;
import coupon.DeleteCouponCommand;

public class CouponMenuCommand implements Command<Void> {
	private Scanner scan;
	private CommandInvoker menuInvoker;
	public CouponMenuCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	public Void execute() {
		while (true) {
			System.out.println("\n=============================================================");
			System.out.println("                        ADMIN DASHBOARD                      ");
			System.out.println("=============================================================");
			System.out.println("1. View Coupons");
			System.out.println("2. Add Coupons");
			System.out.println("3. Update Coupons");
			System.out.println("4. Delete Coupons");
			System.out.println("0. Exit");
			System.out.println("-------------------------------------------------------------");
			System.out.println("Select an option: ");
			
			try {
				int menuChoice = Integer.parseInt(scan.nextLine());
				if(menuChoice == 0) {
					return null;
				}
				switch(menuChoice) {
				case 1:
					menuInvoker.executeCommand(new ViewCouponCommand());
					break;
				case 2:
					menuInvoker.executeCommand(new AddCouponCommand(scan));
					break;
				case 3:
					menuInvoker.executeCommand(new UpdateCouponCommand(scan));
					break;
				case 4:
					menuInvoker.executeCommand(new DeleteCouponCommand(scan));
					break;
				}
			}
			catch (Exception a) {
				System.out.println("Invalid Input, Please add a number between 0-4");	
			}
		}
	}
}
