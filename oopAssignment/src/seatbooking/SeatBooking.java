package seatbooking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.FileIO;
import main.FilePath;

public class SeatBooking {
	private Seat seat;
	private double price;
	private static Map<SeatType, Double > seatPrices = new HashMap<>();
	static { 
		ArrayList<String> csvContent = FileIO.readFile(FilePath.SEAT_PRICES.toString());
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
	
	public SeatBooking(Seat seat) {
		this.seat = seat;
		price = seatPrices.get(seat.getSeatType());
	}
	
	public SeatBooking(Seat seat, double price) {
		this.seat = seat;
		this.price = price;
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
	
	public static Map<SeatType, Double> getSeatPrices(){
		return seatPrices;
	}
	
	public static void seatPricesToCSV() {
		String csvLine = "";
		for(Map.Entry<SeatType,Double> entry: seatPrices.entrySet()) {
			csvLine += entry.getKey()+","+entry.getValue()+"\n";
		}
		FileIO.writeFile(csvLine, FilePath.SEAT_PRICES.toString());
	}
	
	public String toCSV() {
		return seat.getSeatLocation().getRow() + ":" + seat.getSeatLocation().getColumn() + ":" + seat.getSeatType() +":" + price;
	}
	
	public static SeatBooking fromCSV(String content) {
		String[] values = content.split(":");
		SeatLocation csvLocation = new SeatLocation(values[0].charAt(0),Integer.parseInt(values[1]));
		Seat csvSeat = new Seat(csvLocation,SeatType.valueOf(values[2]));
		return new SeatBooking(csvSeat,Double.parseDouble(values[3]));
	}
}
