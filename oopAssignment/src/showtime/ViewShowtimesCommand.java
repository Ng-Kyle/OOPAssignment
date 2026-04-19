package showtime;

import java.util.ArrayList;

import main.Command;

public class ViewShowtimesCommand implements Command<Void> {
	ArrayList<Showtime> filteredShowtimes;
	
	public ViewShowtimesCommand(ArrayList<Showtime> filteredShowtimes) {
		this.filteredShowtimes = filteredShowtimes;
	}
	public Void execute() {
		
		return null;
	}
}
