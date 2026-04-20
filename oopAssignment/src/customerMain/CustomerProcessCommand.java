package customerMain;
import java.util.Scanner;
import concession.*;
import coupon.*;
import main.Command;
import main.CommandInvoker;
import movie.Movie;
import paymentprocessing.*;
import seatbooking.*;
import showtime.Showtime;

import java.util.ArrayList;


public class CustomerProcessCommand implements Command<Void> {
	private  Scanner scan;
	private ArrayList<Movie> availableMovies;
	private ArrayList<Showtime> availableShowtimes;
	private ArrayList<Order> storedOrders;
	private CommandInvoker menuInvoker;
	
	public CustomerProcessCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies,  ArrayList<Showtime> availableShowtimes, ArrayList<Order> storedOrders){
		this.scan = scan;
		this.menuInvoker = menuInvoker;
		this.availableMovies = availableMovies;
		this.availableShowtimes = availableShowtimes;
		this.storedOrders = storedOrders;
	}
	
	
	public Void execute() {
		Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan,menuInvoker));
		if(userMovie == null) {
			return null;
		}
		ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
		if (filteredShowtimes.isEmpty()) {
            System.out.println("No showtimes available for this movie.");
            return null;
        }
		Showtime userShowtime = menuInvoker.executeCommand(new SelectShowtimeCommand(filteredShowtimes,scan));
		ArrayList<SeatBooking> userSeatBookings = menuInvoker.executeCommand(new SelectSeatsCommand(userShowtime.getHall(),scan,storedOrders,userShowtime,menuInvoker));
		ArrayList<ConcessionItem> userItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan,menuInvoker));
		Coupon userCoupon = menuInvoker.executeCommand(new SelectCouponCommand(scan));
		ArrayList<OrderItem> orderedItems = menuInvoker.executeCommand(new ProcessItemsCommand(userItems));
		Order userOrder = new Order(userShowtime, orderedItems, userCoupon.getCode(), userSeatBookings);
		
		Ticket userTicket = menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan,storedOrders));
		if(userTicket == null) {
			return null;
		}
		menuInvoker.executeCommand(new DisplayReceiptCommand(userTicket));
		return null;
	}
}
