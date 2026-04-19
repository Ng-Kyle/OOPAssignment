package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import customerMain.FilterShowtimesCommand;
import customerMain.SelectMovieCommand;
import main.Command;
import main.CommandInvoker;
import movie.Movie;
import paymentprocessing.Order;
import seatbooking.Hall;
import showtime.Showtime;
import seatbooking.ViewSeatLayoutCommand;

public class SeatLayoutMenuCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	ArrayList<Showtime> availableShowtimes; 
	ArrayList<Hall> halls;
	ArrayList<Order> storedOrders;
	Scanner scan;
	CommandInvoker menuInvoker;
	public SeatLayoutMenuCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies, ArrayList<Showtime> availableShowtimes, ArrayList<Hall> halls, ArrayList<Order> storedOrders){
		this.availableMovies = availableMovies;
		this.availableShowtimes = availableShowtimes;
		this.storedOrders = storedOrders;
		this.scan = scan;
		this.menuInvoker = menuInvoker;
		this.halls = halls;
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
					break;
				}
				Showtime chosenShowtime = filteredShowtimes.get(showtimeChoice-1);
				menuInvoker.executeCommand(new ViewSeatLayoutCommand(scan,storedOrders,chosenShowtime));
			}
		}
	}
}
