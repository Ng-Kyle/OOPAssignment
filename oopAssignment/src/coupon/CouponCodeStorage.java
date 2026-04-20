package coupon;

import java.util.ArrayList;

import main.FileIO;
import main.FilePath;

public class CouponCodeStorage {
	private static ArrayList<RealCoupon> availableCoupons = new ArrayList<>();
	static {
		ArrayList<String> csvContent = FileIO.readFile(FilePath.COUPONS.toString());
		for(int i = 0; i < csvContent.size(); i++) {
			availableCoupons.add(RealCoupon.fromCSV(csvContent.get(i)));
		}
	}
	public static void toCSV() {
		String csvLine = "";
		for(int i = 0; i < availableCoupons.size(); i++) {
			csvLine += availableCoupons.get(i).toCSV() + "\n";
		}
		FileIO.writeFile(csvLine, FilePath.COUPONS.toString());
	}
	
	public static ArrayList<RealCoupon> getAllCoupons(){
		return availableCoupons;
	}
	public static Coupon getCouponByCode(String code) {
		for(int i = 0; i < availableCoupons.size(); i++){
			if(code.equals(availableCoupons.get(i).getCode())) {
				return availableCoupons.get(i);
			}
		}
		throw new IllegalArgumentException("No such code!");
	}
	public static void addCoupon(String code, double discountValue, CouponDiscountStrategy discountStrategy) {
		availableCoupons.add(new RealCoupon(code,discountValue,discountStrategy));		
		toCSV();
	}
	public static void removeCoupon(int index) {
		availableCoupons.remove(index-1);
		toCSV();
	}
}
