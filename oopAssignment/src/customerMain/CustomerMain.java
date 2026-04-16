package customerMain;
import java.util.Scanner;
import concession.*;
import coupon.*;
import main.CSVLoader;
import paymentprocessing.*;
import seatbooking.*;
import java.util.ArrayList;


public class CustomerMain {
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Object Construction Testing
		ArrayList<Movie> availableMovies = CSVLoader.readMovies("storedData/movies.csv");
		ArrayList<Hall> halls = CSVLoader.readHalls("storedData/halls.csv");
		ArrayList<Showtime> availableShowtimes = CSVLoader.readShowtimes("storedData/showtimes.csv",availableMovies,halls);
		ArrayList<Ticket> storedTickets = new ArrayList<>();
		ConcessionItemStorage storedItems = new ConcessionItemStorage();
		ItemPriceStorage itemPrice = new ItemPriceStorage();
		
		CustomerInvoker menuInvoker = new CustomerInvoker();
		Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan));
		ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
		Showtime userShowtime = menuInvoker.executeCommand(new SelectShowtimeCommand(filteredShowtimes,scan));
		ArrayList<SeatBooking> userSeatBookings = menuInvoker.executeCommand(new SelectSeatsCommand(userShowtime.getHall(),scan));
		ArrayList<ConcessionItem> userItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan));
		Coupon userCoupon = menuInvoker.executeCommand(new SelectCouponCommand(scan));
		Order userOrder = new Order(userShowtime, userItems, userCoupon, userSeatBookings);
		storedTickets.add(menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan)));
		menuInvoker.executeCommand(new DisplayReceiptCommand(storedTickets.get(storedTickets.size()-1)));
	}
	
}
