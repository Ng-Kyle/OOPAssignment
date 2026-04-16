package seatbooking;

public class Seat {
	private SeatType seatType;
	private SeatLocation seatLocation;
	
	public Seat(SeatLocation seatLocation, SeatType seatType) {
		this.seatType = seatType;
		this.seatLocation = seatLocation;
	}
	public SeatLocation getSeatLocation(){
		return seatLocation;
	}
	public SeatType getSeatType(){
		return seatType;
	}
	public void setSeatType(SeatType seatType){
		this.seatType = seatType;
	}
	public void setSeatLocation(SeatLocation seatLocation){
		this.seatLocation = seatLocation;
	}
	
	public String toString(){
		return String.format("Seat type: seat Location:",seatType, seatLocation); 
	}
}
