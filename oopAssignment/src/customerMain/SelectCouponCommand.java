package customerMain;
import java.util.Scanner;
import coupon.Coupon;
import coupon.CouponFactory;
import coupon.NullCoupon;
import main.Command;

public class SelectCouponCommand implements Command<Coupon> {
	private Scanner scan;
	public SelectCouponCommand(Scanner scan) {
		this.scan = scan;
	}
	public Coupon execute() { //TODO: ASK FOR COUPON CODE
		System.out.println("Do you have a coupon code? Y/N");
		String menuInput = scan.nextLine().trim();
		if(menuInput.isEmpty() || Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
			return new NullCoupon();
		}
		while(true) {
			System.out.println("Input your coupon code");
			menuInput = scan.nextLine().trim();
			try {
				return CouponFactory.selectCoupon(menuInput);
			}
			catch(IllegalArgumentException iae){
				System.out.println("That code doesn't exist! Do you want to try again? Y/N");
				menuInput = scan.nextLine().trim();
				if(menuInput.isEmpty() || Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
					return new NullCoupon();
				}
			}
		}
	}
}
