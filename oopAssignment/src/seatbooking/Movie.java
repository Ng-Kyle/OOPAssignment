package seatbooking;

public class Movie { 
	private String title;
	private String genre;
	private String movieClassification;
	private double durationMinutes;
	
	public Movie(String title) { //testing
		this.title = title;
		genre = "";
		movieClassification = "";
		durationMinutes = 0;
	}
	public String getTitle(){
		return title;
	}
	public String getGenre(){
		return genre;
	}
	public String getMovieClassification(){
		return movieClassification; 
	}
	public double getDurationMinutes(){
		return durationMinutes;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
	public void setMovieClassification(String movieClassification){
		this.movieClassification = movieClassification; 
	}
	public void setDurationMinutes(double durationMinutes){
		this.durationMinutes = durationMinutes;
	}
	
	public String toString() {
		return title;
	}
}
