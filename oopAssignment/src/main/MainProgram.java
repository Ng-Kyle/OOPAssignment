package main;

import java.util.ArrayList;
import java.util.Scanner;

import concession.ConcessionItemStorage;
import concession.ItemPriceStorage;
import coupon.CouponCodeStorage;
import paymentprocessing.Order;
import seatbooking.Hall;
import seatbooking.Movie;
import seatbooking.Showtime;
import customerMain.CustomerProcessCommand;

public class MainProgram {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CommandInvoker menuInvoker = new CommandInvoker();
		ArrayList<Movie> availableMovies = CSVLoader.readMovies("storedData/movies.csv");
		ArrayList<Hall> halls = CSVLoader.readHalls("storedData/halls.csv");
		ArrayList<Showtime> availableShowtimes = CSVLoader.readShowtimes("storedData/showtimes.csv",availableMovies,halls);
		ArrayList<Order> storedOrders = CSVLoader.readOrders("storedData/orders.csv",availableShowtimes);
		new ConcessionItemStorage();
		new ItemPriceStorage();
		new CouponCodeStorage();
		
		int menuChoice = 0;
		while(menuChoice != 3) {
			menuChoice = Integer.parseInt(scan.nextLine());
			switch(menuChoice) {
				case 1:
					menuInvoker.executeCommand(new CustomerProcessCommand(scan,menuInvoker,availableMovies,availableShowtimes,storedOrders));
					break;
				case 2:
					break;
				case 3:
					System.out.println("Thanks for using the system!");
					CSVLoader.saveCSV(availableMovies, "storedData/movies.csv");
					CSVLoader.saveCSV(halls, "storedData/halls.csv");
					CSVLoader.saveCSV(availableShowtimes, "storedData/showtimes.csv");
					CSVLoader.saveCSV(storedOrders, "storedData/order.csv");
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		}
		
	}
}
