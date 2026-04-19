package showtime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import main.CSVLoader;
import main.Command;
import main.FilePath;
import movie.AgeRating;
import movie.Movie;
import seatbooking.Hall;

public class AddShowtimeCommand implements Command<Void> {
	private ArrayList<Showtime> availableShowtimes;
	private Movie userMovie;
	private Scanner scan;
	private ArrayList<Hall> halls;
	public AddShowtimeCommand(Scanner scan, ArrayList<Showtime>availableShowtimes, Movie userMovie, ArrayList<Hall> halls) {
		this.scan = scan;
		this.availableShowtimes = availableShowtimes;
		this.userMovie = userMovie;
		this.halls = halls;
	}
	
	public Void execute() {
		while(true) {
			System.out.println("\n-------------------------ADD NEW SHOWTIME------------------------");
			System.out.println("Available Halls:");
			for (int i = 0; i < halls.size(); i++) {
				System.out.println((i+1) +". Hall " + halls.get(i).getHallID());
			}
			int userChoice = -1;
			while (userChoice < 1 || userChoice > halls.size()) {
				System.out.println("Select a Hall (1-" + halls.size() +"): ");
				try {
					userChoice =Integer.parseInt(scan.nextLine());
				}catch (Exception e) {
					System.out.println("Invalid Input. Please enter a number.");
				}
			}
			Hall userHall = halls.get(userChoice - 1);
			
			LocalDateTime userDateTime = null;
			while(userDateTime == null) {
				try {
					System.out.println("Enter Date (YYYY-MM-DD");
					LocalDate userDate = LocalDate.parse(scan.nextLine());
					System.out.println("Enter Time (HH:MM)");
					LocalTime userTime = LocalTime.parse(scan.nextLine());
					userDateTime = LocalDateTime.of(userDate, userTime);
				}catch (Exception e) {
					System.out.println("Invalid format! Please use YYYY-MM-DD for date and HH:MM for time.");
				}
			}
			
			availableShowtimes.add(new Showtime(userMovie,userHall, userDateTime));
			CSVLoader.saveCSV(availableShowtimes, FilePath.SHOWTIMES.toString());
			System.out.println("Showtime added Successfully");
			System.out.println("Add another showtime for " + userMovie.getTitle() + "? (Y/N): ");
			String menuInput = scan.nextLine().trim().toUpperCase();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
