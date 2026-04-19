package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class AddMovieCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public AddMovieCommand(Scanner scan, ArrayList<Movie> availableMovies){
		this.availableMovies = availableMovies;
		this.scan = scan;
	}
	public Void execute() { //TODO: INPUT VALIDATION
		while(true) {
			String title = scan.nextLine();
			String genre = scan.nextLine();
			AgeRating ageRating = AgeRating.valueOf(scan.nextLine());
			double durationMinutes = Double.parseDouble(scan.nextLine());
			availableMovies.add(new Movie(title,genre,ageRating,durationMinutes));
			CSVLoader.saveCSV(availableMovies, FilePath.MOVIES.toString());
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
