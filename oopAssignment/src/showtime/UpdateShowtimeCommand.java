package showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;
import movie.AgeRating;
import movie.Movie;
import seatbooking.Hall;

public class UpdateShowtimeCommand implements Command<Void> {
	private Scanner scan;
	private ArrayList<Showtime> availableShowtimes;
	private ArrayList<Showtime> filteredShowtimes;
	private ArrayList<Hall> halls;
	
	public UpdateShowtimeCommand(Scanner scan, ArrayList<Showtime> availableShowtimes, ArrayList<Showtime> filteredShowtimes, ArrayList<Hall> halls) {
		this.scan = scan;
		this.availableShowtimes = availableShowtimes;
		this.filteredShowtimes = filteredShowtimes;
		this.halls = halls;
	}
	public Void execute() {
		while(true) {
			int showtimeChoice = Integer.parseInt(scan.nextLine());
			if(showtimeChoice == 0) {
				return null;
			}
			Showtime chosenShowtime = filteredShowtimes.get(showtimeChoice-1);
			while(true) {
				int updateChoice = Integer.parseInt(scan.nextLine());
				if(updateChoice == 3) {
					CSVLoader.saveCSV(availableShowtimes, FilePath.SHOWTIMES.toString());
					break;
				}
				switch(updateChoice) {
				case 1:
					int userChoice = Integer.parseInt(scan.nextLine());//TODO: SHOW HALLS PROBABLY ADD COMMAND FOR IT
					Hall userHall = halls.get(userChoice - 1);
					chosenShowtime.setHall(userHall);
					break;
				case 2:
					System.out.println("YYYY-MM-DD");
					LocalDate userDate = LocalDate.parse(scan.nextLine());
					System.out.println("HH:MM");
					LocalTime userTime = LocalTime.parse(scan.nextLine());
					LocalDateTime userDateTime = LocalDateTime.of(userDate, userTime);
					chosenShowtime.setTime(userDateTime);
					break;
				case 3:
					break;
				default:
					break;
				}	
			}
		}
	}
}
