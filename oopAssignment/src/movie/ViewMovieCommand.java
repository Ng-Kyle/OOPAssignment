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
		System.out.printf("%-4s | %-40s | %-10s | %-10s | %-10s | %-5s%n","NUM", "TITLE", "GENRE", "AGE RATING", "DUR(min)", "ID");
		System.out.println("----------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < availableMovies.size(); i++) {
			Movie m = availableMovies.get(i);
			String rating = (m.getAgeRating() != null) 
                    ? m.getAgeRating().toString().replace("_", "") 
                    : "N/A";
			String title = (m.getTitle() != null) ? m.getTitle() : "Unknown";
			System.out.printf("%-4d | %-40s | %-10s | %-10s | %-10.2f | %-5d%n", 
                    (i + 1), 
                    title,
                    m.getGenre(), 
                    rating,
                    m.getDurationMinutes(), 
                    m.getMovieID());
		}
        
		return null;
	}
	
}