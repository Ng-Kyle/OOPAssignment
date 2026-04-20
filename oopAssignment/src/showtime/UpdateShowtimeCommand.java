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
			System.out.println("\n-------------------------UPDATE SHOWTIME------------------------");
			if (filteredShowtimes.isEmpty()) {
				System.out.println("No SHOWTIME availanle");
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
			
			Showtime chosenShowtime = filteredShowtimes.get(showtimeChoice-1);
			
			while(true) {
				System.out.println("\n**Updating: " + chosenShowtime.getShowtimeID() + "**");
				System.out.println("1. Hall: " + chosenShowtime.getHall().getHallID());
				System.out.println("2. Time: " + chosenShowtime.getTime());
				System.out.println("3. Save and Return ");
				System.out.println("Choice: ");
				
				int updateChoice; 
				try {
						updateChoice= Integer.parseInt(scan.nextLine());
				}catch (Exception e) {
					System.out.println("Invalid Input! Please enter a number.");
					continue;
				}
				
				if(updateChoice == 3) {
					CSVLoader.saveCSV(availableShowtimes, FilePath.SHOWTIMES.toString());
					System.out.println("Changed Save Successfully 😊");
					break;
				}
				switch(updateChoice) {
				case 1:
					System.out.println("Available Halls:");
                    for (int i = 0; i < halls.size(); i++) {
                        System.out.println((i + 1) + ". Hall " + halls.get(i).getHallID());
                    }
                    try {
                        System.out.print("Select New Hall: ");
                        int hallSelect = Integer.parseInt(scan.nextLine());
                        chosenShowtime.setHall(halls.get(hallSelect - 1));
                        System.out.println("Hall updated!");
                    } catch (Exception e) {
                        System.out.println("Invalid Hall selection.");
                    }
                    break;
				case 2:
					try {
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        LocalDate userDate = LocalDate.parse(scan.nextLine());
                        System.out.print("Enter Time (HH:MM): ");
                        LocalTime userTime = LocalTime.parse(scan.nextLine());
                        chosenShowtime.setTime(LocalDateTime.of(userDate, userTime));
                        System.out.println("Time updated!");
                    } catch (Exception e) {
                        System.out.println("Invalid Format! Use YYYY-MM-DD and HH:MM.");
                    }
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;
				}	
			}
		}
	}
}
