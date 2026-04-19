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
			System.out.println("Enter a Title: ");
			String title = scan.nextLine();
			System.out.println("Enter a Genre: ");
			String genre = scan.nextLine();
			
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
			System.out.println("Enter a Duration (Minutes): ");
			double durationMinutes = Double.parseDouble(scan.nextLine());
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
