package customerMain;
import seatbooking.Showtime;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectShowtimeCommand implements Command<Showtime> {
	private ArrayList<Showtime> availableShowtimes;
	private Scanner scan;
	
	public SelectShowtimeCommand(ArrayList<Showtime> availableShowtimes, Scanner scan) {
		this.availableShowtimes = availableShowtimes;
		this.scan = scan;
	}
	@Override
	public Showtime execute() {
		//TODO: DISPLAY AVAILABLE SHOWTIMES
		System.out.println("Pick a showtime");
		int userInput = Integer.parseInt(scan.nextLine());
		return availableShowtimes.get(userInput - 1);
	}

}
