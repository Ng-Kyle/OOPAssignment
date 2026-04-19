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
        
        while(true) {
        	System.out.println("Please select a showtime.");
        	String input = scan.nextLine().trim();
        	
        	if (input.isEmpty()) {
	               System.out.println("Input cannot be empty.");
	               continue;
	           }
        	
        	try {
		        	int userChoice = Integer.parseInt(input);
		        	
		        	
		        	if(userChoice == 0) {
		        		System.out.println("Selection cancelled.");
		        		return null;
		        	}
		        	
			   		if (userChoice >= 1 && userChoice <= filteredShowtimes.size()) {
			   			 	System.out.println("\nShowtime selected succesfully!");
			   			 	return filteredShowtimes.get(userChoice-1);
			   		 	}

		            else{
				          System.out.println("Invalid selection. Please stay within the range.");
		            }
		        
				} catch (NumberFormatException e) {
		            System.out.println("Please enter a valid integer.");
				}
		   }
	  }
	
}