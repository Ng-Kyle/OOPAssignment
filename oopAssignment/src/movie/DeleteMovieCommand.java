package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class DeleteMovieCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public DeleteMovieCommand(Scanner scan, ArrayList<Movie> availableMovies){
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			System.out.println("\n-------------------------DELETE MOVIE------------------------");
			if (availableMovies.isEmpty()) {
				System.out.println("No movies availanle");
				return null;
			}
			for (int i = 0; i < availableMovies.size(); i++) {
				System.out.println((i+1) + ". " + availableMovies.get(i).getTitle());
			}
			System.out.println("Select Movie to delete(1-" + availableMovies.size() + ") or 0 to cancel.");
			
			int movieChoice;
			try {
					movieChoice = Integer.parseInt(scan.nextLine());
					if(movieChoice == 0) {
						return null;
					}
					if (movieChoice < 1 || movieChoice > availableMovies.size()) throw new Exception();
				}catch (Exception a) {
					System.out.println("Invalid Selection! Please enter a number from the list");
					continue;
				}
			Movie movieToDelete = availableMovies.remove(movieChoice-1);
			System.out.println("\nAre you sure you want to DELETE " + movieToDelete + "." ); //confirmation
			System.out.println("TYPE 'YES' to confirm or 'NO' to cancel.");
			String confirmation = scan.nextLine().trim().toUpperCase();
			if (confirmation.equals("YES")) {
				availableMovies.remove(movieChoice-1);
				CSVLoader.saveCSV(availableMovies, FilePath.MOVIES.toString());
				System.out.println("Movie deleted Successfully!!");
			}else {
				System.out.println("Deletion Cancelled.");
			}
			
			System.out.println("\nDo you want to delete another one? (Y/N):");
			String exitInput = scan.nextLine().trim().toUpperCase();
			if(!exitInput.isEmpty() || !exitInput.startsWith("Y")) {
				break;
			}
		}
		return null;
	}
}
