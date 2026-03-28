package seatbooking;

public class SeatFactory {
	public static Seat[] createSeats(int[] column, String[] row) {
		Seat[] seats = new Seat[column.length * row.length];
		int index = 0;
		for(int i = 0; i<row.length; i++) {
			for (int j = 0; j <column.length; j++) {
				seats[index++] = new Seat(new SeatLocation(row[i],column[j]));
			}
		}
		return seats;
	}
}
