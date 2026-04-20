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
		int userChoice = Integer.parseInt(scan.nextLine());
		double userPrice;
		switch(userChoice) {
		case 1:
			userPrice = Double.parseDouble(scan.nextLine());
			seatPrices.replace(SeatType.STANDARD, userPrice);
			SeatBooking.seatPricesToCSV();
			break;
		case 2:
			userPrice = Double.parseDouble(scan.nextLine());
			seatPrices.replace(SeatType.VIP, userPrice);
			SeatBooking.seatPricesToCSV();
			break;
		}
		return null;
	}
}
