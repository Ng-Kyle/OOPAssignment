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
		System.out.println("========NOW SHOWING!!!========="); 
		menuInvoker.executeCommand(new ViewMovieCommand(availableMovies));
		
		//Selection Menu
		int userChoice = -1;
        while (userChoice < 1 || userChoice > availableMovies.size()) {
        	System.out.println("\nPlease select a movie (0 to cancel)");
            
            if (scan.hasNextInt()) {
                userChoice = Integer.parseInt(scan.nextLine());
                if(userChoice == 0) {
                	return null;
                }
                if (userChoice < 1 || userChoice > availableMovies.size()) {
                    System.out.println("Invalid selection. Please stay within the range.");
                }
            } 
            else{
		          System.out.println("Please enter a valid number.");
            }
        }
		
		return availableMovies.get(userChoice - 1);
	}
}
	