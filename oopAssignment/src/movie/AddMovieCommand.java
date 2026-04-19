package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class AddMovieCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public AddMovieCommand(Scanner scan, ArrayList<Movie> availableMovies){
		this.availableMovies = availableMovies;
		this.scan = scan;
	}
	public Void execute() { //TODO: INPUT VALIDATION
		while(true) {
			System.out.println("\n-------------------------ADD NEW MOVIE------------------------");
			
			String title="";
			while(true) {
				System.out.println("Enter Title: ");
				title = scan.nextLine().trim();
				if(!title.isEmpty()) {
					break;
				}
				System.out.println("Title can't be empty!!!");
			}
			String genre="";
			while(true) {
				System.out.println("Enter Genre: ");
				genre = scan.nextLine().trim();
				if(!genre.isEmpty() && !genre.equals("0")) {
					break;
				}
				System.out.println("Invalid Genre! (Cannot be empty or 0)");
			}
			
			AgeRating ageRating = null;
			while(ageRating==null) {
				System.out.println("Select an Age Rating.");
				System.out.println("1. U (General)");
				System.out.println("2. P13 (Parental Guidance) ");
				System.out.println("3. 18 (Adult)");
				System.out.println("Choice: ");
				
				try {
					int ratingChoice = Integer.parseInt(scan.nextLine());
					switch (ratingChoice) {
					case 1:
						ageRating = AgeRating.U;
						break;
					case 2:
						ageRating = AgeRating.P13;
						break;
					case 3:
						ageRating = AgeRating._18;
						break;
					default: 
						System.out.println("Please pick 1, 2 or 3!");
					}
				}catch (Exception e) {
					System.out.println("Invalid Input. Please enter a number!");
				}
			}
			
			double durationMinutes = 0;
	        while (durationMinutes <= 0) {
	            System.out.println("Enter a Duration (Minutes): ");
	            try{
	                durationMinutes = Double.parseDouble(scan.nextLine());
	                if (durationMinutes <= 0) {
	                    System.out.println("Duration must be greater than 0!");
	                }
	            }catch (Exception e) {
	                System.out.println("Invalid Input. Please enter a valid number for minutes!");
	            }
	        }
			availableMovies.add(new Movie(title,genre,ageRating,durationMinutes));
			CSVLoader.saveCSV(availableMovies, FilePath.MOVIES.toString());
			System.out.println("\nMovie Added Successfully 😊 ");
			System.out.println("Add another movie? (Y/N): ");
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
