package customerMain;

import java.util.ArrayList;

import main.FileIO;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import paymentprocessing.Ticket;
import seatbooking.Hall;
import seatbooking.Movie;
import seatbooking.Showtime;

public class DataStorage {
	private ArrayList<Movie> availableMovies = new ArrayList<>();
	private ArrayList<Showtime> availableShowtimes = new ArrayList<>();
	private ArrayList<Hall> halls = new ArrayList<>();
	private ArrayList<Ticket> storedTickets = new ArrayList<>();
	
	public DataStorage() {
		readAvailableMovies();
	}
	
	private void readAvailableMovies() {
		ArrayList<String> csvLines = FileIO.readFile("MovieData.txt");
		for(int i = 0; i<csvLines.size(); i++) {
			availableMovies.add(Movie.fromCSV(csvLines.get(i)));
		}
	}
}
