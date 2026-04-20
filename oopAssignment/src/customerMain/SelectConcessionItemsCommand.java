package customerMain;
import concession.*;
import main.Command;
import main.CommandInvoker;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class SelectConcessionItemsCommand implements Command<ArrayList<ConcessionItem>> {
	private Scanner scan;
	private CommandInvoker menuInvoker;
	
	public SelectConcessionItemsCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	public ArrayList<ConcessionItem> execute(){ 
		ArrayList<ConcessionItem> selectedItems = new ArrayList<>();
		while(true) {
			System.out.println("Do you want to add concession items? Y/N");
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
			menuInvoker.executeCommand(new ViewItemCommand());
			System.out.println("Select Concession Items");
			selectedItems.add(ConcessionItemFactory.selectConcessionItemByIndex(Integer.parseInt(scan.nextLine())-1));
			if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
				int userInput = 0;
				System.out.println("Choose between (1) Medium or (2) Large");
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


