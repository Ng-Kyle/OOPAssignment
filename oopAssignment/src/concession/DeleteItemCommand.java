package concession;

import java.util.ArrayList;
import java.util.Scanner;

import coupon.CouponCodeStorage;
import main.Command;
import showtime.Showtime;

public class DeleteItemCommand implements Command<Void> {

	private Scanner scan;
	public DeleteItemCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		
		ArrayList<ConcessionItem> storedItems = ConcessionItemStorage.getAllItems();
		while(true) {
			System.out.println("\n-------------------------DELETE CONCESSION------------------------");
			if (storedItems.isEmpty()) {
				System.out.println("No items available");
				return null;
			}
			for (int i = 0; i < storedItems.size(); i++) {
                System.out.println((i + 1) + ".  " + storedItems.get(i).getName());
			}
			System.out.println("Select item to delete(1-" + storedItems.size() + ") or 0 to cancel.");
			
			try {
			int itemChoice = Integer.parseInt(scan.nextLine());
			if(itemChoice == 0) {
				break;
			}
			if (itemChoice < 1 || itemChoice > storedItems.size()) {
				throw new Exception();
			}
			ConcessionItem selected = storedItems.get(itemChoice-1);
			System.out.println("\nAre you sure you want to DELETE " + selected.getName() + "." ); //confirmation
			System.out.println("TYPE 'YES' to confirm or 'NO' to cancel.");
			String confirmation = scan.nextLine().trim().toUpperCase();
			
			if (confirmation.isEmpty() || Character.toUpperCase(confirmation.charAt(0)) != 'Y') {
				System.out.println("Deletion Cancelled");
				continue;
			}
			ConcessionItemStorage.removeItem(itemChoice);	
			System.out.println("Deletion was successful!");
					
		}catch (Exception a) {
			System.out.println("Invalid Selection! Please enter a number from the list");
			}
		}
		return null;
	}
}