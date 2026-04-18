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
	//a helper to display the layout of the map
	private void displaySeatMap() {
		System.out.println("\n==================== SEATMAP ====================\n");
		
		//column header
		System.out.print("   ");
		for (int col = 1; col <= 10; col++) {
			System.out.printf("%-4d", col);
		}
		System.out.println("\n-------------------------------------------------");
		
		for (char row = 'A'; row <= 'E'; row++) {
			System.out.print(row + "|"); //this is to show row label
			
			for (int col = 1; col <= 10; col++) {
				Seat seat = hall.findSeat(row, col); 
				
				if (seat != null) {
					System.out.printf("%-4s","[ ]");
				}else {
					System.out.print("    ");
				}
			}
			System.out.println(); //move to the next row
		}
		System.out.println("-------------------------------------------------");
	}
	public ArrayList<SeatBooking> execute() { //TODO:DISPLAY SEATMAP
		int menuInput = 0;
		ArrayList<Seat> userSeats = new ArrayList<>();
		
		while(menuInput != 2) {
			displaySeatMap();
			System.out.println("Select your seats (e.g. A1) or input '2' if done.");
			String seatInput = scan.nextLine();
			
			if(seatInput.equals("2")) {
				break;
			}
			
			try {
			char userRow = seatInput.substring(0,1).toUpperCase().charAt(0);
			int userColumn = Integer.parseInt(seatInput.substring(1));
			Seat selected  = hall.findSeat(userRow, userColumn);
			if (selected !=null) {
				userSeats.add(selected);
				System.out.println("Seat " + seatInput + " added!");
			}else {
				System.out.println("Invalid seat location!");
			}
		}catch (Exception a) {
			System.out.println("Wrong Input, Please use Row+Column (e.g. B5).");
		}
		
		System.out.println("1: Select another seat | 2: Done");
		try {
		    menuInput = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
		    System.out.println("Please enter 1 or 2.");
		    menuInput = 0;
		}
		}
		ArrayList<SeatBooking> userSeatBookings = new ArrayList<>();
		for(Seat seat:userSeats) {
			userSeatBookings.add(new SeatBooking(seat));
		}
		return userSeatBookings;
	}
}
