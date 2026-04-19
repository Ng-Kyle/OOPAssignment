package customerMain;
import concession.*;
import main.Command;
import java.util.Map;
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
		Map<String,Map<SizeType,Double>> showPrice = ItemPriceStorage.getItemPrices();
		
		while(true) {
			System.out.println("Do you want to add concession items? Y/N");
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
			System.out.println("Select Concession Items");
            System.out.println("+==============================================+");
            System.out.println("|                     MENU                     |");
            System.out.println("+==============================================+");;
            for (int i = 0; i < showItems.size(); i++) {
			System.out.println("+==============================================+");
			selectedItems.add(ConcessionItemFactory.selectConcessionItemByIndex(Integer.parseInt(scan.nextLine())-1));
			
			Double price_M = showPrice.get(selectedItems.get(selectedItems.size()-1).getName()).get(SizeType.MEDIUM);
			
			Double price_L = showPrice.get(selectedItems.get(selectedItems.size()-1).getName()).get(SizeType.LARGE);
			
			if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
				int userInput = 0;
	            System.out.println("+==============================================+");
	            System.out.println("|                     SIZE                     |");
	            System.out.println("+==============================================+");;
				System.out.printf("  1.Medium " +  "RM%.2f%n", price_M);           
				System.out.printf("  2.Large " + "RM%.2f%n",price_L);
				System.out.println("+==============================================+");
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
		}
		return selectedItems;
	}
}
			


