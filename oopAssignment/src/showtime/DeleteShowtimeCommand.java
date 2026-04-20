package showtime;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;
import movie.Movie;


public class DeleteShowtimeCommand implements Command<Void> {
	private ArrayList<Showtime> availableShowtimes;
	private ArrayList<Showtime> filteredShowtimes;
	private Scanner scan;
	public DeleteShowtimeCommand(Scanner scan, ArrayList<Showtime>availableShowtimes, ArrayList<Showtime> filteredShowtimes) {
		this.scan = scan;
		this.availableShowtimes = availableShowtimes;
		this.filteredShowtimes = filteredShowtimes;

	}
	public Void execute() {
		while(true) {
			System.out.println("\n-------------------------DELETE SHOWTIME------------------------");
			if (filteredShowtimes.isEmpty()) {
				System.out.println("No movies availanle");
				return null;
			}
			for (int i = 0; i < filteredShowtimes.size(); i++) {
				Showtime s = filteredShowtimes.get(i);
                System.out.println((i + 1) + ". Hall: " + s.getHall().getHallID() + " | Time: " + s.getTime());
			}
			System.out.println("Select Showtime to delete(1-" + filteredShowtimes.size() + ") or 0 to cancel.");
			
			int showtimeChoice;
			try {
				showtimeChoice = Integer.parseInt(scan.nextLine());
					if(showtimeChoice == 0) {
						return null;
					}
					if (showtimeChoice < 1 || showtimeChoice > filteredShowtimes.size()) throw new Exception();
				}catch (Exception a) {
					System.out.println("Invalid Selection! Please enter a number from the list");
					continue;
				}
			Showtime showtimeToDelete = filteredShowtimes.get(showtimeChoice-1);
			
			System.out.println("\nAre you sure you want to DELETE " + showtimeToDelete + "." ); //confirmation
			System.out.println("TYPE 'YES' to confirm or 'NO' to cancel.");
			String confirmation = scan.nextLine().trim().toUpperCase();
			if (confirmation.equals("YES")) {
				availableShowtimes.remove(showtimeToDelete);
				filteredShowtimes.remove(showtimeToDelete);
				CSVLoader.saveCSV(availableShowtimes, FilePath.SHOWTIMES.toString());
				System.out.println("Showtime deleted Successfully!!");
			}else {
				System.out.println("Deletion Cancelled.");
			}
			
			System.out.print("\nDelete another showtime for this movie? (Y/N): ");
            String exitInput = scan.nextLine().trim().toUpperCase();
            if (exitInput.isEmpty() || !exitInput.startsWith("Y")) {
                break;
            }
		}
		return null;
	}
}
