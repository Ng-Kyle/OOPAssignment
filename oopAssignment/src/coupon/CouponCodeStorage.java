package coupon;

import java.util.ArrayList;

import main.FileIO;

public class CouponCodeStorage {
	private static ArrayList<RealCoupon> availableCoupons = new ArrayList<>();
	static {
		ArrayList<String> csvContent = FileIO.readFile("storedData/coupons.csv");
		for(int i = 0; i < csvContent.size(); i++) {
			availableCoupons.add(RealCoupon.fromCSV(csvContent.get(i)));
		}
	}
	public String toCSV() {
		String csvLine = "";
		for(int i = 0; i < availableCoupons.size(); i++) {
			csvLine += availableCoupons.get(i).toCSV() + "\n";
		}
		return csvLine;
	}
	
	public static Coupon getCouponByCode(String code) {
		for(int i = 0; i < availableCoupons.size(); i++){
			if(code.equals(availableCoupons.get(i).getCode())) {
				return availableCoupons.get(i);
			}
			else {
				throw new IllegalArgumentException("No such code!");
			}
		}
		return null; //might need to change this
	}

}
