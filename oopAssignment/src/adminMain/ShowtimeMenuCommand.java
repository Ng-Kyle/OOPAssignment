package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import movie.AddMovieCommand;
import movie.DeleteMovieCommand;
import movie.Movie;
import movie.UpdateMovieCommand;
import movie.ViewMovieCommand;
import showtime.Showtime;
import customerMain.FilterShowtimesCommand;
import customerMain.SelectMovieCommand;
import showtime.ViewShowtimesCommand;
import showtime.AddShowtimeCommand;

public class ShowtimeMenuCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	ArrayList<Showtime> availableShowtimes; 
	Scanner scan;
	CommandInvoker menuInvoker;
	public ShowtimeMenuCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies, ArrayList<Showtime> availableShowtimes){
		this.availableMovies = availableMovies;
		this.availableShowtimes = availableShowtimes;
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	
	public Void execute() { //TODO: DISPLAY MENU OPTIONS
		while(true) {
			Movie userMovie = menuInvoker.executeCommand(new SelectMovieCommand(availableMovies,scan));
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
				case 2:
					menuInvoker.executeCommand(new AddShowtimeCommand(scan, filteredShowtimes));
					break;
				case 3:
					menuInvoker.executeCommand(new UpdateMovieCommand(scan, availableMovies));
					break;
				case 4:
					menuInvoker.executeCommand(new DeleteMovieCommand(scan,availableMovies));
					break;
				default:
					break;
				}
			}
		}
	}
}

