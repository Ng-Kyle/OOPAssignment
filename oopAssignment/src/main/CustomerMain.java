package main;
import java.util.Scanner;

import seatbooking.*;

import java.util.ArrayList;


public class CustomerMain {
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		//Object Construction Testing
		ArrayList<Movie> movies = new ArrayList<>();
		ArrayList<Showtime> showtimes = new ArrayList<>();
		ArrayList<Hall> halls = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		movies.add(new Movie("Among Us"));
		halls.add(new Hall());
		showtimes.add(new Showtime(movies.get(0),halls.get(0)));
		Showtime userShowtime = selectShowtime(selectMovie(movies),showtimes);
		ArrayList<Seat> userSeats = selectSeats(userShowtime.getHall());
		
	}
	
	private static Movie selectMovie(ArrayList<Movie> movies) {
		System.out.println(movies.get(0));
		System.out.println("Pick a movie");
		int userInput = scan.nextInt();
		scan.nextLine();
		return movies.get(userInput);
	}
	
	private static Showtime selectShowtime(Movie movie, ArrayList<Showtime> showtimes) {
			System.out.println(showtimes.get(0));
			System.out.println("Pick a showtime");
			int userInput = scan.nextInt();
			scan.nextLine();
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
			menuInput = scan.nextInt();
		}
		return userSeats;
	}
}
