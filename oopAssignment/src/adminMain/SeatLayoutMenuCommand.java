package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import customerMain.FilterShowtimesCommand;
import customerMain.SelectMovieCommand;
import main.Command;
import main.CommandInvoker;
import movie.Movie;
import paymentprocessing.Order;
import seatbooking.Hall;
import showtime.Showtime;
import seatbooking.ViewSeatLayoutCommand;

public class SeatLayoutMenuCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	ArrayList<Showtime> availableShowtimes; 
	ArrayList<Hall> halls;
	ArrayList<Order> storedOrders;
	Scanner scan;
	CommandInvoker menuInvoker;
	public SeatLayoutMenuCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies, ArrayList<Showtime> availableShowtimes, ArrayList<Hall> halls, ArrayList<Order> storedOrders){
		this.availableMovies = availableMovies;
		this.availableShowtimes = availableShowtimes;
		this.storedOrders = storedOrders;
		this.scan = scan;
		this.menuInvoker = menuInvoker;
		this.halls = halls;
	}
	
	public Void execute() {
		while(true) {
			
			Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan,menuInvoker));
			if(userMovie == null) {
				return null;
			}
			ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
			
			if (filteredShowtimes.isEmpty()) {
	            System.out.println("No showtimes available for this movie.");
	            continue; // Go back to movie selection
	        }
			
	        while (true) {
	            System.out.print("\nSelect a showtime number to view layout (0 to go back): ");
	            String input = scan.nextLine().trim();

	            if (input.isEmpty()) {
	                System.out.println("Input cannot be empty.");
	                continue;
	            }

	            try {
	                int showtimeChoice = Integer.parseInt(input);

	                if (showtimeChoice == 0) {
	                    break; // Breaks internal loop, returns to Movie Selection
	                }

	                // VALIDATION: Check if the number is within the list range
	                if (showtimeChoice < 1 || showtimeChoice > filteredShowtimes.size()) {
	                    System.out.println("Invalid selection. Please choose 1-" + filteredShowtimes.size());
	                    continue;
	                }

	                Showtime chosenShowtime = filteredShowtimes.get(showtimeChoice - 1);
	                
	                // VALIDATION: Check if the object actually exists
	                if (chosenShowtime == null) {
	                    System.out.println("Error: Showtime data is null.");
	                    continue;
	                }

	                // 4. View the layout
	                menuInvoker.executeCommand(new ViewSeatLayoutCommand(storedOrders, chosenShowtime));

	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a valid number.");
		            }
		        }
		    }
		}
	}
