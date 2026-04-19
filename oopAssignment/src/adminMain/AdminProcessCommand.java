package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import movie.Movie;
import paymentprocessing.Order;
import seatbooking.Hall;
import showtime.Showtime;
import seatbooking.ViewSeatLayoutCommand;

public class AdminProcessCommand implements Command<Void> {
	//TODO: Validate Tickets, Manage stock levels, Reports, Set Codes
	private CommandInvoker menuInvoker;
	private  Scanner scan;
	private ArrayList<Movie> availableMovies;
	private ArrayList<Showtime> availableShowtimes;
	private ArrayList<Order> storedOrders;
	private ArrayList<Hall> halls;
	
	public AdminProcessCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies,  ArrayList<Showtime> availableShowtimes, 
								ArrayList<Order> storedOrders, ArrayList<Hall> halls){
		this.scan = scan;
		this.menuInvoker = menuInvoker;
		this.availableMovies = availableMovies;
		this.availableShowtimes = availableShowtimes;
		this.storedOrders = storedOrders;
		this.halls = halls;
	}
	
	public Void execute() {
		while(true) {
			int menuChoice = Integer.parseInt(scan.nextLine());
			switch(menuChoice) {
			case 1:
				menuInvoker.executeCommand(new MovieMenuCommand(scan,menuInvoker,availableMovies));
				break;
			case 2:
				menuInvoker.executeCommand(new ShowtimeMenuCommand(scan,menuInvoker,availableMovies,availableShowtimes,halls));
				break;

			case 3:
				menuInvoker.executeCommand(new SeatLayoutMenuCommand(scan,menuInvoker,availableMovies,availableShowtimes,halls,storedOrders));
				break;
			case 4:
				menuInvoker.executeCommand(new ConcessionItemMenuCommand(scan,menuInvoker));
				break;
			case 5:
				menuInvoker.executeCommand(new StockQuantityMenuCommand(scan,menuInvoker));
				break;
			case 6:
				menuInvoker.executeCommand(new OrderMenuCommand(scan,menuInvoker,storedOrders));
				break;
			case 7:
				menuInvoker.executeCommand(new CouponMenuCommand(scan,menuInvoker));
			}
			break;
		}
		return null;
	}
}
