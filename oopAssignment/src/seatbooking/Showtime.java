package seatbooking;
import java.time.LocalDateTime;

public class Showtime {
	private Movie movie;
	private Hall hall;
	private LocalDateTime time;
	
	public Showtime(Movie movie, Hall hall) { //testing
		this.movie = movie;
		this.hall = hall;
		time = LocalDateTime.now();
	}
	public Hall getHall() {
		return hall;
	}
	public String toString() {
		return movie.toString();
	}
}
