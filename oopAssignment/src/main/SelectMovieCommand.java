package main;
import java.util.ArrayList;
import java.util.Scanner;

import seatbooking.Movie;

public class SelectMovieCommand implements Command<Movie> {

	private ArrayList<Movie> availableMovies;
	private Scanner scan;
	public SelectMovieCommand(ArrayList<Movie> availableMovies, Scanner scan) {
		this.availableMovies = availableMovies;
		this.scan = scan;
	}
	public Movie execute() {
		System.out.println("Select a movie"); //TODO: Selection Menu
		int userChoice = Integer.parseInt(scan.nextLine());
		return availableMovies.get(userChoice);
	}
}
