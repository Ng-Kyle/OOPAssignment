package main;

import java.util.ArrayList;

import paymentprocessing.Order;
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
	
	public static ArrayList<Order> readOrders(String filePath, ArrayList<Showtime> storedShowtimes){
		ArrayList<String> contents = FileIO.readFile(filePath);
		ArrayList<Order> storedOrders = new ArrayList<>();
		for(int i = 0; i < contents.size(); i++){
			storedOrders.add(Order.fromCSV(contents.get(i),storedShowtimes));
		}
		return storedOrders;
	}
	
	public static <T extends CSVStored> void saveCSV(ArrayList<T> data, String filePath) {
		String csvData = "";
		for(int i = 0; i < data.size(); i++) {
			csvData += data.get(i).toCSV() + "\n";
		}
		FileIO.writeFile(csvData, filePath);
		
	}
}
