package adminMain;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import movie.AddMovieCommand;
import movie.Movie;
import movie.UpdateMovieCommand;
import movie.DeleteMovieCommand;
import movie.ViewMovieCommand;

public class MovieMenuCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	CommandInvoker menuInvoker;
	public MovieMenuCommand(Scanner scan, CommandInvoker menuInvoker, ArrayList<Movie> availableMovies){
		this.availableMovies = availableMovies;
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	
	public Void execute() { //TODO: DISPLAY MENU OPTIONS
		while(true) {
			int menuChoice = Integer.parseInt(scan.nextLine());
			if(menuChoice == 0) {
				return null;
			}
			switch(menuChoice) {
			case 1:
				menuInvoker.executeCommand(new ViewMovieCommand(availableMovies));
			case 2:
				menuInvoker.executeCommand(new AddMovieCommand(scan, availableMovies));
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
