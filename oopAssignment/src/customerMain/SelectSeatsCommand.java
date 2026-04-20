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
		ArrayList<Seat> takenSeats = new ArrayList<>();
		for(int i = 0; i < storedOrders.size(); i++) {
			if(storedOrders.get(i).getShowtime().getShowtimeID() == chosenShowtime.getShowtimeID()) {
				ArrayList<SeatBooking> orderSeatBookings = storedOrders.get(i).getSeatBooking();
				for(int j = 0; j < orderSeatBookings.size(); j++) {
					takenSeats.add(orderSeatBookings.get(j).getSeat());
				}
			}
		}
		
		menuInvoker.executeCommand(new ViewSeatLayoutCommand(storedOrders, chosenShowtime));
		int menuInput = 0;
		ArrayList<Seat> userSeats = new ArrayList<>();
		
		while(menuInput != 2) {
			System.out.println("\nSelect your seats (e.g. A1) or input '2' if done.");
			String seatInput = scan.nextLine().trim().toUpperCase();
			
			if(seatInput.equals("2")) {
				break;
			}
			
			try {
			char userRow = seatInput.substring(0,1).toUpperCase().charAt(0);
			int userColumn = Integer.parseInt(seatInput.substring(1));
			Seat selected  = hall.findSeat(userRow, userColumn);
			if (selected !=null) {
				boolean alreadyTaken = false;
				for (Seat ts : takenSeats) {
					if (ts.getSeatLocation().toString().equals(selected.getSeatLocation().toString())) {
						alreadyTaken = true;
						break;
					}
				}
				
				boolean alreadyInList = false;
				for (Seat us : userSeats) {
					if (us.getSeatLocation().toString().equals(selected.getSeatLocation().toString())) {
						alreadyInList = true;
						break;
					}
				}
				if(alreadyTaken) {
					System.out.println("Error: Seat " + seatInput + " is already booked by another customer!");
				}else if (alreadyInList) {
					System.out.println("Error: You have already selected " + seatInput + " for this booking!");
				}else {
					userSeats.add(selected);
                    System.out.println("Seat " + seatInput + " added successfully!");
				}
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
