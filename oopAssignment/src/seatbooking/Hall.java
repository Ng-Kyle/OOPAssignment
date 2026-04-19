package seatbooking;

import main.CSVStored;

public class Hall implements CSVStored{
	private ScreenSize screenSize;
	private Seat[] seats;
	private int hallID;
	private static int totalHalls = 1;
	private int columns;
	private int rows;
	
	public Hall() {
		columns = 1;
		rows = 1;
		seats = SeatFactory.createSeats(1,1);
		screenSize = null;
		hallID = totalHalls++;
	}
	
	public Hall(int hallID, ScreenSize screenSize, Seat[] seats, int columns, int rows) {
		this.screenSize = screenSize;
		this.seats = seats;
		this.hallID = hallID;
		this.columns = columns;
		this.rows = rows;
		totalHalls++;
	}
	
	public Hall(ScreenSize screenSize, Seat[] seats, int columns, int rows) {
		this.screenSize = screenSize;
		this.seats = seats;
		this.columns = columns;
		this.rows = rows;
		hallID = totalHalls++;
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
	
	public String toString() {
		return String.format("Hall ID: " + hallID);
	}
	
	public Seat findSeat(char row, int column) {
		for(int i = 0; i<seats.length; i++) {
			SeatLocation seatLocation = seats[i].getSeatLocation();
			if(seatLocation.getRow() == row && seatLocation.getColumn() == column){
				return seats[i];
			}
		}
		return null;
	}
	
	public String toCSV() {
		return hallID + "," + screenSize + "," + rows + "," + columns; 
	}
	
	
	public static Hall fromCSV(String content) {
		String[] values = content.split(",");
		int storedRows = Integer.parseInt(values[2]);
		int storedColumns = Integer.parseInt(values[3]);
		
		return new Hall(Integer.parseInt(values[0]), ScreenSize.valueOf(values[1]),SeatFactory.createSeats(storedRows, storedColumns), storedRows, storedColumns);
	}
	
}
