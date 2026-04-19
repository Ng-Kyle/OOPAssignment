package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import movie.Movie;
import seatbooking.Hall;
import showtime.*;
import showtime.Showtime;
import customerMain.FilterShowtimesCommand;
import customerMain.SelectMovieCommand;


public class ShowtimeMenuCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	ArrayList<Showtime> availableShowtimes; 
	ArrayList<Hall> halls;
	Scanner scan;
	CommandInvoker menuInvoker;
	public ShowtimeMenuCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies, ArrayList<Showtime> availableShowtimes, ArrayList<Hall> halls){
		this.availableMovies = availableMovies;
		this.availableShowtimes = availableShowtimes;
		this.scan = scan;
		this.menuInvoker = menuInvoker;
		this.halls = halls;
	}
	
	public Void execute() { //TODO: DISPLAY MENU OPTIONS
		while(true) {
			Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan,menuInvoker));
			if(userMovie == null) {
				return null;
			}
			
			ArrayList<Showtime> filteredShowtimes = menuInvoker.executeCommand(new FilterShowtimesCommand(userMovie,availableShowtimes));
			while(true) {
				System.out.println("----------------------------SHOWTIME-----------------------------");
				System.out.println("Movie: " + userMovie.getTitle());
				System.out.println("1. View Current Showtimes");
				System.out.println("2. Add New Showtime");
				System.out.println("3. Update Existing Showtime");
				System.out.println("4. Delete Showtime");
				System.out.println("0. Back to Movie Selection");
				System.out.println("Choice: ");
				
				int menuChoice;
				try {
						menuChoice = Integer.parseInt(scan.nextLine());
				}catch (Exception e) {
					System.out.println("Invalid Input. Please enter a number between 0-4!");
					continue;
				}
				if(menuChoice == 0) {
					break;
				}	
				switch(menuChoice) {
				case 1:
					menuInvoker.executeCommand(new ViewShowtimesCommand(filteredShowtimes));
					break;
				case 2:
					menuInvoker.executeCommand(new AddShowtimeCommand(scan, availableShowtimes, userMovie,halls));
					break;
				case 3:
					if (filteredShowtimes.isEmpty()) {
						System.out.println("No showtimes available for this movie.");
					}else {
						menuInvoker.executeCommand(new UpdateShowtimeCommand(scan, availableShowtimes, filteredShowtimes, halls));
						break;
					}
				case 4:
					if (filteredShowtimes.isEmpty()) {
						System.out.println("No showtimes available for this movie.");
					}else {
						menuInvoker.executeCommand(new DeleteShowtimeCommand(scan,availableShowtimes,filteredShowtimes));
						break;
					}
					;
				default:
					break;
				}
			}
		}
	}
}

