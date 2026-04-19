package seatbooking;

import java.util.ArrayList;
import java.util.Scanner;

import customerMain.FilterShowtimesCommand;
import customerMain.SelectMovieCommand;
import main.Command;
import main.CommandInvoker;
import movie.Movie;
import paymentprocessing.Order;
import showtime.Showtime;

public class ViewSeatLayoutCommand implements Command<Void> {
	private ArrayList<Hall> halls;
	private ArrayList<Order> storedOrders;
	private ArrayList<Showtime> availableShowtimes;
	private ArrayList<Movie> availableMovies;
	private CommandInvoker menuInvoker;
	private Scanner scan;
	
	public ViewSeatLayoutCommand(Scanner scan, CommandInvoker menuInvoker,ArrayList<Movie> availableMovies, ArrayList<Hall> halls, ArrayList<Order> storedOrders, ArrayList<Showtime> availableShowtimes) {
		this.scan = scan;
		this.availableMovies = availableMovies;
		this.menuInvoker = menuInvoker;
		this.halls = halls;
		this.storedOrders = storedOrders;
		this.availableShowtimes = availableShowtimes;
	}
	public Void execute() {
		while(true) {
			Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan,menuInvoker));
			if(userMovie == null) {
				return null;
			}
			ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
			
			while(true) {
				int showtimeChoice = Integer.parseInt(scan.nextLine());
				if(showtimeChoice == 0) {
					return null;
				}
				Showtime chosenShowtime = filteredShowtimes.get(showtimeChoice-1);
				ArrayList<Seat> takenSeats = new ArrayList<>();
				for(int i = 0; i < storedOrders.size(); i++) {
					if(storedOrders.get(i).getShowtime().getShowtimeID() == chosenShowtime.getShowtimeID()) {
						ArrayList<SeatBooking> orderSeatBookings = storedOrders.get(i).getSeatBooking();
						for(int j = 0; j < orderSeatBookings.size(); j++) {
							takenSeats.add(orderSeatBookings.get(j).getSeat());
						}
					}
				}
				Seat[] showtimeSeats = chosenShowtime.getHall().getSeats();
				for(int i = 0; i<showtimeSeats.length; i++) {
					SeatLocation seatLocation = showtimeSeats[i].getSeatLocation();
					boolean seatAvailable = true;
					for(int j = 0; j < takenSeats.size(); j++) {
						if(seatLocation.equals(takenSeats.get(j).getSeatLocation())) {
							seatAvailable = false;
							break;
						}
					}
					if(i != 0 && seatLocation.getRow() != showtimeSeats[i-1].getSeatLocation().getRow()) {
						System.out.print("\n");
					}
					if(seatAvailable == true) {
						System.out.print(seatLocation.toString() + "   ");
					}
					else {
						System.out.print("X    ");
					}					
				}
				
			}
		}
	}
}
