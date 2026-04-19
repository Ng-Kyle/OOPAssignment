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
				int menuChoice = Integer.parseInt(scan.nextLine());
				if(menuChoice == 0) {
					return null;
				}
				switch(menuChoice) {
				case 1:
					menuInvoker.executeCommand(new ViewShowtimesCommand(filteredShowtimes));
					break;
				case 2:
					menuInvoker.executeCommand(new AddShowtimeCommand(scan, availableShowtimes, userMovie,halls));
					break;
				case 3:
					menuInvoker.executeCommand(new UpdateShowtimeCommand(scan, availableShowtimes, filteredShowtimes, halls));
					break;
				case 4:
					menuInvoker.executeCommand(new DeleteShowtimeCommand(scan,availableShowtimes,filteredShowtimes));
					break;
				default:
					break;
				}
			}
		}
	}
}

