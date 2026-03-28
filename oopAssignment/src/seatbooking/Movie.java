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
	public String toString() {
		return title;
	}
}
