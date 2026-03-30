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
		ArrayList<Movie> movies = new ArrayList<>();
		ArrayList<Showtime> showtimes = new ArrayList<>();
		ArrayList<Hall> halls = new ArrayList<>();
		movies.add(new Movie("Among Us"));
		halls.add(new Hall());
		showtimes.add(new Showtime(movies.get(0),halls.get(0)));
		Showtime userShowtime = selectShowtime(selectMovie(movies),showtimes);
		ArrayList<Seat> userSeats = selectSeats(userShowtime.getHall());
		ArrayList<SeatBooking> userSeatBookings = new ArrayList<>();
		for(Seat seat:userSeats) {
			userSeatBookings.add(new SeatBooking(seat,TicketType.ADULT));
		}
		ArrayList<OrderItem> userItems = new ArrayList<>();
		ConcessionItem chosenItem = ConcessionItemFactory.selectConcessionItem(Integer.parseInt(scan.nextLine()));
		userItems.add(new OrderItem(chosenItem,SizeType.MEDIUM));
		Coupon userCoupon = CouponFactory.selectCoupon(scan.nextLine());
		Order userOrder = new Order(userShowtime, userItems, userCoupon, userSeatBookings);
		System.out.println(userOrder.calculateFinalPrice());
		
		
	}
	
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
	
	
}
