package seatbooking;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Showtime {
	private int showtimeID;
	private static int totalShowtimes = 0;
	private Movie movie;
	private Hall hall;
	private LocalDateTime time;
	
	public Showtime(Movie movie, Hall hall, LocalDateTime time) { 
		this.movie = movie;
		this.hall = hall;
		this.time = time;
		showtimeID = totalShowtimes++;
	}
	
	public Showtime(int showtimeID, Movie movie, Hall hall, LocalDateTime time) { 
		this.movie = movie;
		this.hall = hall;
		this.time = time;
		this.showtimeID = showtimeID;
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
	
	public String toCSV() {
		return movie.getTitle() + "," + hall.getHallID() + "," + time;
	}
	
	public static Showtime fromCSV(String content, ArrayList<Movie> availableMovies, ArrayList<Hall> availableHalls) {
		String[] values = content.split(",");
		Movie storedMovie = null;
		Hall storedHall = null;
		
		for(int i = 0; i < availableMovies.size(); i++) {
			if(values[1].equals(availableMovies.get(i).getTitle())) { //TODO: replace this with get id instead
				storedMovie = availableMovies.get(i);
				break;
			}			
		}
		if(storedMovie == null) {
			throw new IllegalArgumentException("No corresponding movies found!");
		}
		for(int i = 0; i < availableHalls.size(); i++) {
			if(Integer.parseInt(values[2]) == availableHalls.get(i).getHallID()) {
				storedHall = availableHalls.get(i);
				break;
			}
		}
		
		if(storedHall == null) {
			throw new IllegalArgumentException("No corresponding halls found!");
		}
		
		return new Showtime(Integer.parseInt(values[0]),storedMovie, storedHall, LocalDateTime.parse(values[3]));
	}
	
}
	