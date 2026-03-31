package seatbooking;

public class Movie { 
	private String title;
	private String genre;
	private AgeRating ageRating;
	private double durationMinutes;
	
	public Movie(String title, String genre, AgeRating ageRating, double durationMinutes) { //testing
		this.title = title;
		this.genre = genre;
		this.ageRating = ageRating;
		this.durationMinutes = durationMinutes;
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
	
	public String toString() {
		return title;
	}
	public static Movie fromCSV(String content) {
		String[] values = content.split(",");
		AgeRating ageRating = null;
		switch(values[2]) {
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
		return new Movie(values[0],values[1],ageRating,Double.parseDouble(values[3]));
	}
}
