package customerMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import seatbooking.Hall;
import seatbooking.Seat;
import seatbooking.SeatBooking;

public class SelectSeatsCommand implements Command<ArrayList<SeatBooking>> {
	private Hall hall;
	private Scanner scan;
	public SelectSeatsCommand(Hall hall, Scanner scan) {
		this.hall = hall;
		this.scan = scan;
	}
	public ArrayList<SeatBooking> execute() { //TODO:DISPLAY SEATMAP
		int menuInput = 0;
		ArrayList<Seat> userSeats = new ArrayList<>();
		System.out.println("Select your seats, input 2 if done.");
		while(menuInput != 2) {
			String seatInput = scan.nextLine();
			char userRow = seatInput.substring(0,1).charAt(0);
			int userColumn = Integer.parseInt(seatInput.substring(1));
			userSeats.add(hall.findSeat(userRow, userColumn));
			menuInput = Integer.parseInt(scan.nextLine());
		}
		ArrayList<SeatBooking> userSeatBookings = new ArrayList<>();
		for(Seat seat:userSeats) {
			userSeatBookings.add(new SeatBooking(seat));
		}
		return userSeatBookings;
	}
}
