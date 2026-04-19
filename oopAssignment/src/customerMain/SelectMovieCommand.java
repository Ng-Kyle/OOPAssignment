package customerMain;
import java.util.ArrayList;
import java.util.Scanner;

import movie.ViewMovieCommand;
import main.Command;
import main.CommandInvoker;
import movie.Movie;

public class SelectMovieCommand implements Command<Movie> {

	private CommandInvoker menuInvoker;
	private ArrayList<Movie> availableMovies;
	private Scanner scan;
	public SelectMovieCommand(ArrayList<Movie> availableMovies, Scanner scan, CommandInvoker menuInvoker) {
		this.availableMovies = availableMovies;
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	public Movie execute() {
	    System.out.println("========================================NOW SHOWING!!!========================================"); 
	    System.out.println("----------------------------------------------------------------------------------------------");
	    
	    // Display the list using the invoker
	    menuInvoker.executeCommand(new ViewMovieCommand(availableMovies));

	    while (true) {
	        System.out.print("\nPlease select a movie number (1-" + availableMovies.size() + ") or 0 to cancel: ");
	        String input = scan.nextLine().trim();

	        if (input.isEmpty()) {
	            System.out.println("Input cannot be empty.");
	            continue;
	        }

	        try {
	            int userChoice = Integer.parseInt(input);

	            if (userChoice == 0) {
	                System.out.println("Selection cancelled.");
	                return null;
	            }

	            if (userChoice >= 1 && userChoice <= availableMovies.size()) {
	                // Success: return the selected movie
	                return availableMovies.get(userChoice - 1);
	            } else {
	                System.out.println("Invalid selection. Please stay within the range.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Please enter a valid integer.");
	        }
	    }
	}
}