package paymentprocessing;

import java.util.ArrayList;

import concession.ConcessionItem;
import concession.ConcessionItemFactory;
import concession.IndividualItem;
import coupon.Coupon;
import coupon.CouponFactory;
import seatbooking.SeatBooking;
import seatbooking.Showtime;
import main.CSVStored;

public class Order implements CSVStored{
	private int orderID;
	private Showtime showtime;
	private ArrayList<OrderItem> orderedConcessionItems = new ArrayList<>();
	private String coupon;
	private ArrayList<SeatBooking> seatBookings = new ArrayList<>();
	private double finalPrice;
	private boolean used = false;
	private static int totalOrders = 1;
	
	public Order(Showtime showtime, ArrayList<OrderItem> orderedConcessionItems, String coupon, ArrayList<SeatBooking> seatBookings) {
		this.showtime = showtime;
		this.orderedConcessionItems = orderedConcessionItems;
		this.coupon = coupon;
		this.seatBookings = seatBookings;
		orderID = totalOrders++;
	}
	
	public Order(int orderID, Showtime showtime, ArrayList<OrderItem> orderedConcessionItems, String coupon, ArrayList<SeatBooking> seatBookings, double finalPrice, boolean used) {
		this.orderID = orderID;
		this.showtime = showtime;
		this.orderedConcessionItems = orderedConcessionItems;
		this.coupon = coupon;
		this.seatBookings = seatBookings;
		this.finalPrice = finalPrice;
		this.used = used;
		totalOrders++;
		
	}
	
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	
	public double calculateFinalPrice() {
		finalPrice = 0;
		for(int i = 0; i < seatBookings.size(); i++) {
			finalPrice += seatBookings.get(i).getPrice();
		}
		for(int i = 0; i < orderedConcessionItems.size(); i++) {
			finalPrice += orderedConcessionItems.get(i).getItemPrice();
		}
		if(coupon.isEmpty()) {
			return finalPrice;
		}
		Coupon orderCoupon = CouponFactory.selectCoupon(coupon);
		finalPrice = orderCoupon.applyCoupon(finalPrice);
		return finalPrice;
	}
	
	public void reduceStock() {
		for(OrderItem concessionItem:orderedConcessionItems) {
			ConcessionItem tempItem = ConcessionItemFactory.selectConcessionItemByName(concessionItem.getOrderedItem());
			if(tempItem == null) {
				continue;
			}
			if(tempItem instanceof IndividualItem) {
				tempItem.setSize(concessionItem.getSize());
			}
			tempItem.reduceStock(concessionItem.getQuantity());
		}
	}	
	
	public int getOrderID() {
		return orderID;
	}
	public String toString() {
		return showtime.toString();//TODO: Nigel/Emily make a CSV format for this
	}
	
	public String toCSV() {
		String orderItemsCSV = "";
		for(int i = 0; i<orderedConcessionItems.size();i++) {
			orderItemsCSV += orderedConcessionItems.get(i).toCSV();
			if(i < orderedConcessionItems.size()-1) {
				orderItemsCSV += ";";
			}
		}
		String seatBookingCSV = "";
		for(int i = 0; i<seatBookings.size(); i++) {
			seatBookingCSV += seatBookings.get(i).toCSV();
			if(i < seatBookings.size()-1) {
				seatBookingCSV +=";";
			}
		}
		return orderID + "," + showtime.getShowtimeID() + "," + orderItemsCSV + "," + coupon + "," + seatBookingCSV + "," + finalPrice + "," + used;
	}
	public static Order fromCSV(String contents, ArrayList<Showtime> storedShowtimes) {
		String[] values = contents.split(",");
		Showtime csvShowtime = new Showtime();
		for(int i = 0; i < storedShowtimes.size(); i++) {
			if(storedShowtimes.get(i).getShowtimeID() == Integer.parseInt(values[1])) {
				csvShowtime = storedShowtimes.get(i);
				break;
			}
		}

		ArrayList<OrderItem> csvOrderItems = new ArrayList<>();
		if(!values[2].isEmpty()) {
			String[] orderItems = values[2].split(";");
			for(int i = 0; i < orderItems.length; i++) {
				csvOrderItems.add(OrderItem.fromCSV(orderItems[i]));
			}
		}
		
		ArrayList<SeatBooking> csvSeatBookings = new ArrayList<>();
		String[] seatBookings = values[4].split(";");
		for(int i = 0; i< seatBookings.length; i++) {
			csvSeatBookings.add(SeatBooking.fromCSV(seatBookings[i]));
		}
		
		return new Order(Integer.parseInt(values[0]),csvShowtime,csvOrderItems,values[3],csvSeatBookings,Double.parseDouble(values[5]),Boolean.parseBoolean(values[6]));
	}
}
