package customerMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import paymentprocessing.Order;
import seatbooking.Hall;
import seatbooking.Seat;
import seatbooking.SeatBooking;
import showtime.Showtime;
import seatbooking.ViewSeatLayoutCommand;

public class SelectSeatsCommand implements Command<ArrayList<SeatBooking>> {
	private Hall hall;
	private Scanner scan;
	private CommandInvoker menuInvoker;
	private ArrayList<Order> storedOrders;
	private Showtime chosenShowtime;
	public SelectSeatsCommand(Hall hall, Scanner scan, ArrayList<Order> storedOrders, Showtime chosenShowtime,CommandInvoker menuInvoker) {
		this.hall = hall;
		this.scan = scan;
		this.storedOrders = storedOrders;
		this.chosenShowtime = chosenShowtime;
		this.menuInvoker = menuInvoker;
	}
	
	public ArrayList<SeatBooking> execute() { 
		menuInvoker.executeCommand(new ViewSeatLayoutCommand(storedOrders, chosenShowtime));
		int menuInput = 0;
		ArrayList<Seat> userSeats = new ArrayList<>();
		
		while(menuInput != 2) {
			System.out.println("\nSelect your seats (e.g. A1) or input '2' if done.");
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
		}//TODO: This is still missing prevention of duplicate seats
		}
		ArrayList<SeatBooking> userSeatBookings = new ArrayList<>();
		for(Seat seat:userSeats) {
			userSeatBookings.add(new SeatBooking(seat));
		}
		return userSeatBookings;
	}
}
