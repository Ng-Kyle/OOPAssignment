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
<<<<<<< Upstream, based on origin/master
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
=======
		availableMovies.add(new Movie("Among Us"));
>>>>>>> 3730c27 System flow seperated into individual commands
		halls.add(new Hall());
<<<<<<< Upstream, based on origin/master
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
=======
		availableShowtimes.add(new Showtime(availableMovies.get(0),halls.get(0)));
=======
		ArrayList<Ticket> storedTickets = new ArrayList<>();
>>>>>>> da6f9d2 QRCode and file IO methods
		
		CustomerInvoker menuInvoker = new CustomerInvoker();
		Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan));
		ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
		Showtime userShowtime = menuInvoker.executeCommand(new SelectShowtimeCommand(filteredShowtimes,scan));
		ArrayList<SeatBooking> userSeatBookings = menuInvoker.executeCommand(new SelectSeatsCommand(userShowtime.getHall(),scan));
		ArrayList<ConcessionItem> userItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan));
		Coupon userCoupon = menuInvoker.executeCommand(new SelectCouponCommand(scan));
>>>>>>> 3730c27 System flow seperated into individual commands
		Order userOrder = new Order(userShowtime, userItems, userCoupon, userSeatBookings);
<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
		storedTickets.add(menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan)));
		menuInvoker.executeCommand(new DisplayReceiptCommand(storedTickets.get(storedTickets.size()-1)));
=======
		double finalPrice = userOrder.calculateFinalPrice();
		System.out.println(finalPrice);
		processPayment(finalPrice,userOrder);
		
		
		
		
>>>>>>> afc18c6 basic customer system flow complete
=======
		storedTickets.add(menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan)));
		menuInvoker.executeCommand(new DisplayReceiptCommand(storedTickets.get(storedTickets.size()-1)));
>>>>>>> da6f9d2 QRCode and file IO methods
	}
	
<<<<<<< Upstream, based on origin/master
=======
	private static Movie selectMovie(ArrayList<Movie> movies) {
		System.out.println(movies.get(0));
		System.out.println("Pick a movie");
		int userInput = Integer.parseInt(scan.nextLine());
		
		return movies.get(userInput);
	}
	
	private static Showtime selectShowtime(Movie movie, ArrayList<Showtime> showtimes) {
			System.out.println(showtimes.get(0));
			System.out.println("Pick a showtime");
			int userInput = Integer.parseInt(scan.nextLine());
			return showtimes.get(userInput);
		
	}
	private static ArrayList<Seat> selectSeats(Hall hall) {
		int menuInput = 0;
		ArrayList<Seat> userSeats = new ArrayList<>();
		System.out.println("Select your seats, input 2 if done.");
		while(menuInput != 2) {
			String seatInput = scan.nextLine();
			String userRow = seatInput.substring(0,1);
			int userColumn = Integer.parseInt(seatInput.substring(1));
			userSeats.add(hall.findSeat(userRow, userColumn));
			menuInput = Integer.parseInt(scan.nextLine());
		}
		return userSeats;
	}
	private static void processPayment(double finalPrice, Order order) {
		double userPayment = Double.parseDouble(scan.nextLine());
		if(userPayment<finalPrice) {
			System.out.println("Funds insufficient. Order was cancelled.");
		}
		System.out.println("Order successful! Enjoy your movie.");
		order.reduceStock();
		System.out.println(ConcessionItemStorage.getStockQuantity());
=======
		menuInvoker.executeCommand(new PaymentProcessCommand(userOrder,scan));
>>>>>>> 3730c27 System flow seperated into individual commands
	}
	
>>>>>>> afc18c6 basic customer system flow complete
}
