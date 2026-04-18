package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;

public class ViewMovieCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public ViewMovieCommand(ArrayList<Movie> availableMovies){
		this.availableMovies = availableMovies;

	}
	public Void execute() {//TODO: DISPLAY ALL MOVIES STORED IN AVAILABLE MOVIES
		return null;
	}
}
