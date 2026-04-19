package main;

import java.util.ArrayList;
import java.util.Scanner;

import concession.ConcessionItemStorage;
import concession.ItemPriceStorage;
import coupon.CouponCodeStorage;
import paymentprocessing.Order;
import seatbooking.Hall;
import showtime.Showtime;
import customerMain.CustomerProcessCommand;
import movie.Movie;
import adminMain.AdminProcessCommand;

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
		
		
		while(true) {
			System.out.println("==============================================");
			System.out.println("         WELCOME TO EXPLOSIVE CINEMAS         ");
			System.out.println("==============================================");
			System.out.println("1. Customer");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
		
			int menuChoice = Integer.parseInt(scan.nextLine());
			switch(menuChoice) {
			case 1:
				menuInvoker.executeCommand(new CustomerProcessCommand(scan,menuInvoker,availableMovies,availableShowtimes,storedOrders));
				break;
			case 2:
				menuInvoker.executeCommand(new AdminProcessCommand(scan,menuInvoker,availableMovies,availableShowtimes,storedOrders,halls));
				break;
			case 3:
				System.out.println("Thanks for using the system!");
				CSVLoader.saveCSV(availableMovies, "storedData/movies.csv");
				CSVLoader.saveCSV(halls, "storedData/halls.csv");
				CSVLoader.saveCSV(availableShowtimes, "storedData/showtimes.csv");
				CSVLoader.saveCSV(storedOrders, "storedData/order.csv");
				return;
			default:
				System.out.println("Invalid input!");
			}
		}
	}
}
