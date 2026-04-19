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
			//TODO: Show available halls here
			int userChoice = Integer.parseInt(scan.nextLine());
			Hall userHall = halls.get(userChoice - 1);
			System.out.println("YYYY-MM-DD");
			LocalDate userDate = LocalDate.parse(scan.nextLine());
			System.out.println("HH:MM");
			LocalTime userTime = LocalTime.parse(scan.nextLine());
			LocalDateTime userDateTime = LocalDateTime.of(userDate, userTime);
			availableShowtimes.add(new Showtime(userMovie,userHall, userDateTime));
			CSVLoader.saveCSV(availableShowtimes, FilePath.SHOWTIMES.toString());
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
