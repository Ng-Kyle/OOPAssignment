package customerMain;
import concession.*;
import main.Command;

import java.util.Scanner;
import java.util.ArrayList;

public class SelectConcessionItemsCommand implements Command<ArrayList<ConcessionItem>> {
	private Scanner scan;
	
	public SelectConcessionItemsCommand(Scanner scan) {
		this.scan = scan;
	}
	public ArrayList<ConcessionItem> execute(){ //TODO: SHOW AVAILABLE ITEMS
		ArrayList<ConcessionItem> selectedItems = new ArrayList<>();
		
		ArrayList<ConcessionItem> showItems = ConcessionItemStorage.getAllItems();
		
		while(true) {
			System.out.println("Do you want to add concession items? Y/N");
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
			System.out.println("Select Concession Items");
			System.out.println("+======================+");
			for(int i = 0; i < showItems.size() ;i++) {
				System.out.println("|"+(i+1)+"."+showItems.get(i).getName()+"|");
			}
			System.out.println("+======================+");
			selectedItems.add(ConcessionItemFactory.selectConcessionItemByIndex(Integer.parseInt(scan.nextLine())-1));
			if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
				int userInput = 0;
				System.out.println("Select Size");
				System.out.println("1.Large | 2.Medium");
				userInput = Integer.parseInt(scan.nextLine());
				switch(userInput) {
				case 1:
					selectedItems.get(selectedItems.size()-1).setSize(SizeType.MEDIUM);
					break;
				case 2:
					selectedItems.get(selectedItems.size()-1).setSize(SizeType.LARGE);
					break;
				default:
					System.out.println("Invaild.Please choose between Medium or Large");
					continue; //TODO:Add validation later
				}
			}
		}
		return selectedItems;
	}
}
