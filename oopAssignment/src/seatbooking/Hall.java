package seatbooking;

public class Hall {
	private ScreenSize screenSize;
	private Seat[] seats;
	private int hallID;
	
	public Hall() {
		seats = SeatFactory.createSeats(new int[]{1, 2, 3}, new String[]{"A","B","C"} );
		screenSize = null;
		hallID = 0;
	}
	public Seat[] getSeats() {
		return seats;
	}
	public ScreenSize getScreenSize(){
		return screenSize;
	}

	public int getHallID(){
		return hallID;
	}
	public void setScreenSize(ScreenSize screenSize){
		this.screenSize = screenSize;
	}
	public void setSeat(Seat[] seats){
		this.seats = seats;
	}
	public void setHallID(int hallID){
		this.hallID = hallID;
	}
	
	public Seat findSeat(String row, int column) {
		for(int i = 0; i<seats.length; i++) {
			SeatLocation seatLocation = seats[i].getSeatLocation();
			if(seatLocation.getRow().equalsIgnoreCase(row) && seatLocation.getColumn() == column){
				return seats[i];
			}
		}
		return null;
	}
}
