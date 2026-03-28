package seatbooking;

public class SeatLocation {
	private String row;
	private int column;
	
	public SeatLocation(String row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public String getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
}
