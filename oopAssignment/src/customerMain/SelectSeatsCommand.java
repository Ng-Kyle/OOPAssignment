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
			System.out.println("\nSelect your seats (e.g. A1).");
			String seatInput = scan.nextLine().trim().toUpperCase();
			try {
				char userRow = seatInput.substring(0,1).toUpperCase().charAt(0);
				int userColumn = Integer.parseInt(seatInput.substring(1));
				Seat selected  = hall.findSeat(userRow, userColumn);
				if (selected !=null) {
					for (Seat ts : takenSeats) {
						if (ts.getSeatLocation().toString().equals(selected.getSeatLocation().toString())) {
							throw new SeatUnavailableException();
						}
					}
					for (Seat us : userSeats) {
						if (us.getSeatLocation().toString().equals(selected.getSeatLocation().toString())) {
							throw new DuplicateSeatException();
						}
					}
					userSeats.add(selected);
                    System.out.println("Seat " + seatInput + " added successfully!");
				}
				else {
					System.out.println("Invalid seat location!");
				}
			}
			catch (SeatUnavailableException sue) {
				System.out.println("Error: Seat " + seatInput + " is already booked by another customer!");
				continue;
			}
			catch (DuplicateSeatException dse) {
				System.out.println("Error: Seat " + seatInput + " is already booked by another customer!");
				continue;
			}
			catch (Exception e) {
				System.out.println("Wrong Input, Please use Row+Column (e.g. B5).");
				continue;
			}
			while(true) {
				System.out.println("1: Select another seat | 2: Done");
				try {
					menuInput = Integer.parseInt(scan.nextLine());
					break;
				}
				catch (Exception e) {
					System.out.println("Please enter 1 or 2.");
					menuInput = 0;
				}
			}
		}
		ArrayList<SeatBooking> userSeatBookings = new ArrayList<>();
		for(Seat seat:userSeats) {
			userSeatBookings.add(new SeatBooking(seat));
		}
		return userSeatBookings;
	}
}
