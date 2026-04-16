package main;

import java.util.ArrayList;

import seatbooking.Hall;
import seatbooking.Movie;
import seatbooking.Showtime;

public class CSVLoader {
	public static ArrayList<Movie> readMovies(String filePath){
		ArrayList<String> contents = FileIO.readFile(filePath);
		ArrayList<Movie> availableMovies = new ArrayList<>();
		for(int i = 0; i < contents.size(); i++) {
		 	availableMovies.add(Movie.fromCSV(contents.get(i)));
		}
		return availableMovies;
	}
	
	public static ArrayList<Hall> readHalls(String filePath){
		ArrayList<String> contents = FileIO.readFile(filePath);
		ArrayList<Hall> halls = new ArrayList<>();
		for(int i = 0; i < contents.size(); i++) {
			halls.add(Hall.fromCSV(contents.get(i)));
		}
		return halls;
	}
	public static ArrayList<Showtime> readShowtimes(String filePath, ArrayList<Movie> availableMovies, ArrayList<Hall> halls){
		ArrayList<String> contents = FileIO.readFile(filePath);
		ArrayList<Showtime> availableShowtimes = new ArrayList<>();
		for(int i = 0; i<contents.size();i++) {
			availableShowtimes.add(Showtime.fromCSV(contents.get(i),availableMovies,halls));
		}
		return availableShowtimes;
	}
}
