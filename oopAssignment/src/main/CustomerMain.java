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
<<<<<<< Upstream, based on origin/master
		ArrayList<Ticket> storedTickets = new ArrayList<>();
		
		CustomerInvoker menuInvoker = new CustomerInvoker();
		Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan));
		ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
		Showtime userShowtime = menuInvoker.executeCommand(new SelectShowtimeCommand(filteredShowtimes,scan));
		ArrayList<SeatBooking> userSeatBookings = menuInvoker.executeCommand(new SelectSeatsCommand(userShowtime.getHall(),scan));
		ArrayList<ConcessionItem> userItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan));
		Coupon userCoupon = menuInvoker.executeCommand(new SelectCouponCommand(scan));
=======
		movies.add(new Movie("Among Us"));
		halls.add(new Hall());
		showtimes.add(new Showtime(movies.get(0),halls.get(0)));
		Showtime userShowtime = selectShowtime(selectMovie(movies),showtimes);
		ArrayList<Seat> userSeats = selectSeats(userShowtime.getHall());
		ArrayList<SeatBooking> userSeatBookings = new ArrayList<>();
		for(Seat seat:userSeats) {
			userSeatBookings.add(new SeatBooking(seat));
		}
		ArrayList<ConcessionItem> userItems = new ArrayList<>();
		userItems.add(ConcessionItemFactory.selectConcessionItem(Integer.parseInt(scan.nextLine())));
		if(userItems.get(0) instanceof IndividualItem) {
			userItems.get(0).setSize(SizeType.MEDIUM); //TODO: CHANGE THIS TO USER INPUT
		}
		Coupon userCoupon = CouponFactory.selectCoupon(scan.nextLine());
>>>>>>> 41c216c testing why is it im 4 behind even when i just open up my eclipse and just pull
		Order userOrder = new Order(userShowtime, userItems, userCoupon, userSeatBookings);
		storedTickets.add(menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan)));
		menuInvoker.executeCommand(new DisplayReceiptCommand(storedTickets.get(storedTickets.size()-1)));
	}
	
}
