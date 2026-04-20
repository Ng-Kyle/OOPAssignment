package coupon;

import java.util.ArrayList;

import main.Command;

public class ViewCouponCommand implements Command<Void> {
	
	public Void execute() {
        System.out.println("\n=============================================================");
        System.out.println("                      AVAILABLE COUPONS                      ");
        System.out.println("=============================================================");

        try {
            ArrayList<RealCoupon> coupons = CouponCodeStorage.getAllCoupons();


	        if (coupons == null || coupons.isEmpty()) {
	            System.out.println("No coupons found in the system.");

            
            } else {
                int count = 0;
                for (RealCoupon coupon : coupons) {
                    if (coupon != null && coupon.getCode() != null) {
                        System.out.println(coupon.toString());
                        System.out.println("-------------------------------------------------------------");
                        count++;
                    }
                }
                System.out.println("Total Coupons Displayed: " + count);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while retrieving coupons: " + e.getMessage());
        }

        return null;
    }
}
