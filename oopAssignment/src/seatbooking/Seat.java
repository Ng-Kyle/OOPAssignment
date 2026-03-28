package seatbooking;

public class Seat {
	private SeatType seatType;
	private SeatLocation seatLocation;
	
	public Seat(SeatLocation seatLocation) {
		seatType = SeatType.STANDARD;
		this.seatLocation = seatLocation;
	}
	public SeatLocation getSeatLocation(){
		return seatLocation;
	}
}
