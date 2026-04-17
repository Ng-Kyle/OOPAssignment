package seatbooking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.FileIO;

public class SeatBooking {
	private Seat seat;
	private double price;
	private static Map<SeatType, Double > seatPrices = new HashMap<>();
	static { 
		ArrayList<String> csvContent = FileIO.readFile("storedData/seatPrices.csv");
		for(int i =0; i<csvContent.size();i++) {
			String[] values = csvContent.get(i).split(",");
			seatPrices.put(SeatType.valueOf(values[0]),Double.parseDouble(values[1]));
		}
	}
	public double getPrice() {
		return price;
	}
	public SeatBooking(){
		seat = null;
		price = 0.0;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public Seat getSeat(){
		return seat;
	}
	public void setSeat(Seat seat){
		this.seat = seat; 
	}
	
	public SeatBooking(Seat seat) {
		this.seat = seat;
		price = seatPrices.get(seat.getSeatType());
	}
	
	public String seatPricesToCSV() {
		String csvLine = "";
		for(Map.Entry<SeatType,Double> entry: seatPrices.entrySet()) {
			csvLine += entry.getKey()+","+entry.getValue()+"\n";
		}
		return csvLine;
	}
	
}
