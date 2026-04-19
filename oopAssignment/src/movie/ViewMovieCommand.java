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
		System.out.printf("%-4s | %-20s | %-10s | %-10s | %-10s | %-5s%n","NUM", "TITLE", "GENRE", "AGE RATING", "DUR(min)", "ID");
		System.out.println("----------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < availableMovies.size(); i++) {
			Movie m = availableMovies.get(i);
			System.out.printf("%-4d | %-20s | %-10s | %-10s | %-10.2f | %-5d%n", 
                    (i + 1), 
                    m.getTitle(), 
                    m.getGenre(), 
                    m.getAgeRating(), 
                    m.getDurationMinutes(), 
                    m.getMovieID());
}
        
		return null;
	}
}
