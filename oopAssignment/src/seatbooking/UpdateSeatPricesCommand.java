package seatbooking;

import java.util.Map;
import java.util.Scanner;

import main.Command;

public class UpdateSeatPricesCommand implements Command<Void> {
	private Scanner scan;
	public UpdateSeatPricesCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		Map<SeatType, Double> seatPrices = SeatBooking.getSeatPrices();
		while(true) {
			System.out.println("\n-------------------------UPDATE SEAT PRICES------------------------");
			System.out.printf("1. Standard Seat : RM%.2f%n", seatPrices.get(SeatType.STANDARD));
			System.out.printf("2. VIP Seat : RM%.2f%n", seatPrices.get(SeatType.VIP));
			System.out.println("0. Save and return");
			System.out.println("Select Seat Type to update.");
			try {
				int userChoice = Integer.parseInt(scan.nextLine());
				if (userChoice==0) {
					SeatBooking.seatPricesToCSV();
					System.out.println("Updates Saved Successfully!!");
					break;
				}
				double userPrice;
				switch(userChoice) {
				case 1:
					System.out.println("Enter new STANDARD price: RM");
					userPrice = Double.parseDouble(scan.nextLine());
					seatPrices.replace(SeatType.STANDARD, userPrice);
					SeatBooking.seatPricesToCSV();
					System.out.println("Updated Succesfully");
					break;
				case 2:
					System.out.println("Enter new VIP price: RM");
					userPrice = Double.parseDouble(scan.nextLine());
					seatPrices.replace(SeatType.VIP, userPrice);
					SeatBooking.seatPricesToCSV();
					System.out.println("Updated Succesfully");
					break;
				default:
					System.out.println("Invalid Selection. Please choose 1-2!");
				}
			}catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value.");
			}catch (Exception e) {
				System.out.println("Error occured. Please try again.");
			}
		} 
		return null;
	}
}
