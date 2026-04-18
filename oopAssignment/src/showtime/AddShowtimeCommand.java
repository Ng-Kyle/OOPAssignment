package showtime;

import java.util.ArrayList;
import java.util.Scanner;

import main.Command;

public class AddShowtimeCommand implements Command<Void> {
	ArrayList<Showtime> filteredShowtimes;
	Scanner scan;
	public AddShowtimeCommand(Scanner scan, ArrayList<Showtime>filteredShowtimes) {
		this.scan = scan;
		this.filteredShowtimes = filteredShowtimes;
	}
	
	public Void execute() {
		
	}
}
