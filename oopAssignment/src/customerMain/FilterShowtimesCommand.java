package customerMain;
import seatbooking.Movie;
import seatbooking.Showtime;
import java.util.ArrayList;

import main.Command;

public class FilterShowtimesCommand implements Command<ArrayList<Showtime>>{
	private ArrayList<Showtime> availableShowtimes;
	private Movie userMovie;
	
	public FilterShowtimesCommand(Movie userMovie, ArrayList<Showtime> availableShowtimes) {
		this.userMovie = userMovie; 
		this.availableShowtimes = availableShowtimes;
	}
	@Override
	public ArrayList<Showtime> execute() {
		ArrayList<Showtime> filteredShowtimes = new ArrayList<>();  
		for(int i=0; i<availableShowtimes.size();i++) {
			if(userMovie.equals(availableShowtimes.get(i).getMovie())) {
				filteredShowtimes.add(availableShowtimes.get(i));
			}
		}
		return filteredShowtimes;
	}

}


