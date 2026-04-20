package movie;

import main.CSVStored;

public class Movie implements CSVStored{ 
	private int movieID;
	private String title;
	private String genre;
	private AgeRating ageRating;
	private double durationMinutes;
	private static int totalMovies = 1;
	
	public Movie(String title, String genre, AgeRating ageRating, double durationMinutes) { //testing
		this.title = title;
		this.genre = genre;
		this.ageRating = ageRating;
		this.durationMinutes = durationMinutes;
		movieID = totalMovies++;
	}
	public Movie(int movieID,String title, String genre, AgeRating ageRating, double durationMinutes) { //testing
		this.title = title;
		this.genre = genre;
		this.ageRating = ageRating;
		this.durationMinutes = durationMinutes;
		this.movieID = movieID;
		totalMovies++;
	}
	public String getTitle(){
		return title;
	}
	public String getGenre(){
		return genre;
	}
	public AgeRating getAgeRating(){
		return ageRating; 
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
	public void setMovieClassification(AgeRating ageRating){
		this.ageRating = ageRating; 
	}
	public void setDurationMinutes(double durationMinutes){
		this.durationMinutes = durationMinutes;
	}
	
	public int getMovieID() {
		return movieID;
	}
	public String toString() {
		return title;
	}
	
	public String toCSV() {
		return movieID + "," + title + "," + genre + "," + ageRating + "," + durationMinutes;
	}
	public static Movie fromCSV(String content) {
		String[] values = content.split(",");
		AgeRating ageRating = null;
		switch(values[3]) {
		case "U":
			ageRating = AgeRating.U;
			break;
		case "P13":
			ageRating = AgeRating.P13;
			break;
		case "18":
			ageRating = AgeRating._18;
			break;
		}
		return new Movie(Integer.parseInt(values[0]),values[1],values[2],ageRating,Double.parseDouble(values[4]));
	}
}
