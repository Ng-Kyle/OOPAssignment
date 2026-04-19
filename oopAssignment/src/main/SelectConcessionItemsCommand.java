package main;
import concession.*;
import java.util.Scanner;
import java.util.ArrayList;

public class SelectConcessionItemsCommand implements Command<ArrayList<ConcessionItem>> {
	private Scanner scan;
	public SelectConcessionItemsCommand(Scanner scan) {
		this.scan = scan;
	}
	public ArrayList<ConcessionItem> execute(){
		ArrayList<ConcessionItem> selectedItems = new ArrayList<>();
		int menuInput = 0;
		while(menuInput != 4) {
			
			System.out.println("+==========================+");
			System.out.println("+			MENU		   +");
;			System.out.println("+==========================+");
			System.out.println("|Select Concession Items   |");
			System.out.println("|1.Popcorn				   |");
			System.out.println("|2.Drink				   |");
			System.out.println("|3.Combo			       |");
			System.out.println("|4.No adding 		       |");
			System.out.println("+==========================+");
			
			System.out.println("Press 4 to stop adding items.");
			menuInput = Integer.parseInt(scan.nextLine());
			
			if(menuInput == 4) {
					break;
			}
			
			selectedItems.add(ConcessionItemFactory.selectConcessionItem(Integer.parseInt(scan.nextLine())));
			if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
				int userInput = 0;
				System.out.println("===================================");
				System.out.println("Select Size");
				userInput = Integer.parseInt(scan.nextLine());
				switch(userInput) {
				case 1:
					selectedItems.get(selectedItems.size()-1).setSize(SizeType.MEDIUM);
					break;
				case 2:
					selectedItems.get(selectedItems.size()-1).setSize(SizeType.LARGE);
					break;
				default:
					System.out.println("Invaild. Please choose the size"); //TODO:Add validation later
					
				}
				System.out.println("===================================");
			}
			

		}
		return selectedItems;
	}
}
