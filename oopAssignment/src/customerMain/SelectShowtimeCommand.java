package customerMain;
import java.util.ArrayList;
import java.util.Scanner;

import main.Command;

import showtime.Showtime;

public class SelectShowtimeCommand implements Command<Showtime> {
	private ArrayList<Showtime> filteredShowtimes;
	private Scanner scan;
	public SelectShowtimeCommand( ArrayList<Showtime> filteredShowtimes, Scanner scan) {
		
		this.scan = scan;
		this.filteredShowtimes = filteredShowtimes;
		
	}
	@Override
	public Showtime execute() {
		//TODO: DISPLAY AVAILABLE SHOWTIMES
		

        for (int i = 0; i < filteredShowtimes.size(); i++) {
            Showtime st = filteredShowtimes.get(i);

            System.out.println((i + 1) + ". "
                + st.getTime() + " | "
                + st.getHall());
        }
		
		int userChoice = -1;
		
        while (userChoice < 1 || userChoice > filteredShowtimes.size()) {
        	System.out.println("\nPlease select a showtime");
        	
            
            if (scan.hasNextInt()) {
                userChoice = Integer.parseInt(scan.nextLine());
                if (userChoice < 1 || userChoice > filteredShowtimes.size()) {
                    System.out.println("Invalid selection. Please stay within the range.");
                }
            } 
            else{
		          System.out.println("Please enter a valid number.");
            }
        }
		
		return filteredShowtimes.get(userChoice - 1);
	}


}
