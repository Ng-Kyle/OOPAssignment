package seatbooking;


public class SeatFactory {
	public static Seat[] createSeats(int row, int column) { 
		if(row > 26 && row < 0) {
			throw new IllegalArgumentException("Minimum of 1 and maximum of 26 rows!");
		}
		if(column < 0) {
			throw new IllegalArgumentException("Minimum of 1 column!");
		}
		
		Seat[] seats = new Seat[column * row];
		int index = 0;
		//VIP Range middle 33% of hall
		int vipRowStart = row/3;
		int vipRowEnd =  2 * row/3;
			
		for(int i = 0; i<row; i++) {
			for (int j = 0; j <column; j++) {
				char rowCode = (char) ('A' + i);
				SeatLocation location = new SeatLocation(rowCode,j + 1);
				SeatType type;
				if(i < vipRowStart || i > vipRowEnd) {
					type = SeatType.STANDARD;
				}else {
					type = SeatType.VIP;
				}
				seats[index++] = new Seat(location, type);
			}
		}
		return seats;
	}
}
