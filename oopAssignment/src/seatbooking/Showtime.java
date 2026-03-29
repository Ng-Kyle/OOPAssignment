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
	public Showtime(){
		movie = null;  
		hall = null;
		time = null;
	}
	public Movie getMovie(){
		return movie;
	}
	
	public LocalDateTime getTime(){
		return time;
	}
	public void setMovie(Movie movie){
		this.movie = movie;
	}
	public void setHall(Hall hall){
		this.hall = hall;
	}
	public void setTime(LocalDateTime time){
		this.time = time; 
	}
	
	
	public String toString() {
		return movie.toString();
	}
	
	
}
