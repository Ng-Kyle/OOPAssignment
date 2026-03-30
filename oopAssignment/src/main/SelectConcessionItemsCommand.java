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
		while(menuInput != 2) {
			System.out.println("Select Concession Items");
			selectedItems.add(ConcessionItemFactory.selectConcessionItem(Integer.parseInt(scan.nextLine())));
			if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
				int userInput = 0;
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
					break; //TODO:Add validation later
				}
			}
			
			System.out.println("Press 2 to stop adding items.");
			menuInput = Integer.parseInt(scan.nextLine());
		}
		return selectedItems;
	}
}
