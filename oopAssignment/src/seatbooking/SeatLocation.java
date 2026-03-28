package seatbooking;

public class SeatLocation {
	private String row;
	private int column;
	
	public SeatLocation(String row, int column) {
		this.row = row;
		this.column = column;
	}
	public SeatLocation(){
		row = "";
		column = 0;
	}
	
	public String getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public void setRow(String row){
		this.row = row;
	}
	public void setColumn(int column){
		this.column = column;
	}
}
