package main;
import java.util.Scanner;
import concession.*;
import coupon.*;
import paymentprocessing.*;
import seatbooking.*;
import java.util.ArrayList;


public class CustomerMain {
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Object Construction Testing
		ArrayList<Movie> availableMovies = new ArrayList<>();
		ArrayList<Showtime> availableShowtimes = new ArrayList<>();
		ArrayList<Hall> halls = new ArrayList<>();
		availableMovies.add(new Movie("Among Us"));
		halls.add(new Hall());
		availableShowtimes.add(new Showtime(availableMovies.get(0),halls.get(0)));
		
		CustomerInvoker menuInvoker = new CustomerInvoker();
		Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan));
		ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
		Showtime userShowtime = menuInvoker.executeCommand(new SelectShowtimeCommand(filteredShowtimes,scan));
		ArrayList<SeatBooking> userSeatBookings = menuInvoker.executeCommand(new SelectSeatsCommand(userShowtime.getHall(),scan));
		ArrayList<ConcessionItem> userItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan));
		Coupon userCoupon = menuInvoker.executeCommand(new SelectCouponCommand(scan));
		Order userOrder = new Order(userShowtime, userItems, userCoupon, userSeatBookings);
		menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan));
	}
	
}
