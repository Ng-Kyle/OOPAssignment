package showtime;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;


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
			int showtimeChoice = Integer.parseInt(scan.nextLine());
			if(showtimeChoice == 0) {
				CSVLoader.saveCSV(availableShowtimes, FilePath.SHOWTIMES.toString());
				return null;
			}
			Showtime chosenShowtime = filteredShowtimes.get(showtimeChoice-1);
			for(int i = 0; i < availableShowtimes.size(); i++) {
				if(chosenShowtime.equals(availableShowtimes.get(i))) {
					availableShowtimes.remove(i);
				}
			}
			
		}
	}
}
