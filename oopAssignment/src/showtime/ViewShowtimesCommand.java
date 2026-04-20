package showtime;

import java.util.ArrayList;

import main.Command;


public class ViewShowtimesCommand implements Command<Void> {
	ArrayList<Showtime> filteredShowtimes;
	
	public ViewShowtimesCommand(ArrayList<Showtime> filteredShowtimes) {
		this.filteredShowtimes = filteredShowtimes;
	}
	public Void execute() {
		System.out.printf("%-4s | %-20s | %-10s | %-20s | %-5s%n","NUM", "TITLE", "HALL", "TIME", "ID");
		System.out.println("---------------------------------------------------------------------------------");
		
		for (int i = 0; i < filteredShowtimes.size(); i++) {
			Showtime s = filteredShowtimes.get(i);
			System.out.printf("%-4d | %-20s | %-10s | %-10s | %-5d%n", 
                    (i + 1), 
                    s.getMovie(), 
                    s.getHall(), 
                    s.getTime(), 
                    s.getShowtimeID());
		
		}
		return null;
	}
}