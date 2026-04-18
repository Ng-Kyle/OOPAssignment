package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class DeleteMovieCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public DeleteMovieCommand(Scanner scan, ArrayList<Movie> availableMovies){ //TODO: ADD CONFIRMATION BEFORE DELETION
		this.availableMovies = availableMovies;
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			int movieChoice = Integer.parseInt(scan.nextLine());
			if(movieChoice == 0) {
				return null;
			}
			availableMovies.remove(movieChoice-1);
			CSVLoader.saveCSV(availableMovies, FilePath.MOVIES.toString());
		}
	}
}
