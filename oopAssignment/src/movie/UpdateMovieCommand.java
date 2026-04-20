package movie;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class UpdateMovieCommand implements Command<Void> {
	ArrayList<Movie> availableMovies;
	Scanner scan;
	public UpdateMovieCommand(Scanner scan, ArrayList<Movie> availableMovies){
		this.availableMovies = availableMovies;
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			System.out.println("\n-------------------------UPDATE MOVIE------------------------");
			if (availableMovies.isEmpty()) {
				System.out.println("No movies availanle");
				return null;
			}
			for (int i = 0; i < availableMovies.size(); i++) {
				System.out.println((i+1) + ". " + availableMovies.get(i).getTitle());
			}
			System.out.println("Select Movie (1-" + availableMovies.size() + ") or 0 to exit.");
			
			int movieChoice;
			try {
					movieChoice = Integer.parseInt(scan.nextLine());
					if(movieChoice == 0) {
						return null;
					}
					if (movieChoice < 1 || movieChoice > availableMovies.size()) throw new Exception();
				}catch (Exception a) {
					System.out.println("Invalid Selection! Please enter a number from the list");
					continue;
				}	
			Movie chosenMovie = availableMovies.get(movieChoice-1);
			while(true) {
				System.out.println("\n**Updating: " + chosenMovie.getTitle() + "**");
				System.out.println("1. Title: " + chosenMovie.getTitle());
				System.out.println("2. Genre: " + chosenMovie.getGenre());
				System.out.println("3. Age Rating: " + chosenMovie.getAgeRating());
				System.out.println("4. Duration: " + chosenMovie.getDurationMinutes());
				System.out.println("5. Save and Return ");
				System.out.println("Choice: ");
				
				int updateChoice; 
				try {
						updateChoice= Integer.parseInt(scan.nextLine());
				}catch (Exception e) {
					System.out.println("Invalid Input! Please enter a number.");
					continue;
				}
				if(updateChoice == 5) {
					CSVLoader.saveCSV(availableMovies, FilePath.MOVIES.toString());
					System.out.println("Changed Save Successfully 😊");
					break;
				}
				switch(updateChoice) {
				case 1:
					while(true) {
						System.out.println("Enter new Title: ");
						String newTitle = scan.nextLine().trim();
						if(!newTitle.isEmpty()) {
							chosenMovie.setTitle(newTitle);
							break;
						}
						System.out.println("Title can't be empty!!!");
					}
					break;
				case 2:
					while(true) {
						System.out.println("Enter new Genre: ");
						String newGenre = scan.nextLine().trim();
						if(!newGenre.isEmpty() && !newGenre.equals("0")) {
							chosenMovie.setGenre(newGenre);
							break;
						}
						System.out.println("Invalid Genre! (Cannot be empty or 0)");
					}
					break;
				case 3:
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
					chosenMovie.setMovieClassification(ageRating);
					break;
				case 4:
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
					chosenMovie.setDurationMinutes(durationMinutes);
					break;
				case 5:
					break;
				default:
					break;
				}	
			}
		}
	}
}
