package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class UpdateMovieCommand implements Command<Void> { //TODO:INPUT VALIDATION AND DISPLAY MOVIE DETAILS
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public UpdateMovieCommand(Scanner scan, ArrayList<Movie> availableMovies){
		this.availableMovies = availableMovies;
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			int movieChoice = Integer.parseInt(scan.nextLine());
			if(movieChoice == 0) {
				return null;
			}
			Movie chosenMovie = availableMovies.get(movieChoice-1);
			while(true) {
				int updateChoice = Integer.parseInt(scan.nextLine());
				if(updateChoice == 5) {
					CSVLoader.saveCSV(availableMovies, FilePath.MOVIES.toString());
					break;
				}
				switch(updateChoice) {
				case 1:
					chosenMovie.setTitle(scan.nextLine());
					break;
				case 2:
					chosenMovie.setGenre(scan.nextLine());
					break;
				case 3:
					chosenMovie.setMovieClassification(AgeRating.valueOf(scan.nextLine()));
					break;
				case 4:
					chosenMovie.setDurationMinutes(Double.parseDouble(scan.nextLine()));
					break;
				case 5:
					break;
				default:
					break;
				}	
			}
		}
	}
}
