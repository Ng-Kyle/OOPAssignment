package seatbooking;

public class SeatLocation {
	private char row;
	private int column;
	
	public SeatLocation(char row, int column) {
		this.row = row;
		this.column = column;
	}
	public SeatLocation(){
		row = ' ';
		column = 0;
	}
	
	public char getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public void setRow(char row){
		this.row = row;
	}
	public void setColumn(int column){
		this.column = column;
	}
}
