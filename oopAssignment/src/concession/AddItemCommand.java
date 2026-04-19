package concession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import main.Command;


public class AddItemCommand implements Command<Void> {
	private Scanner scan;
	public AddItemCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			int itemType = Integer.parseInt(scan.nextLine());
			switch(itemType) {
			case 1:
				String itemName = scan.nextLine();
				UnitType itemUnit = UnitType.valueOf(scan.nextLine());//TODO:MAKE THIS A SWITCH
				int strategyChoice = Integer.parseInt(scan.nextLine());
				StockReductionStrategy itemReductionStrategy = null;
				switch(strategyChoice) {
				case 1:
					itemReductionStrategy = new PopcornReductionStrategy();
					break;
				case 2:
					itemReductionStrategy = new DrinkReductionStrategy();
					break;
				default:
				}
				Map<SizeType,Double> sizePrices = new HashMap<>();
				sizePrices.put(SizeType.MEDIUM, Double.parseDouble(scan.nextLine()));
				sizePrices.put(SizeType.LARGE, Double.parseDouble(scan.nextLine()));
				IndividualItem newItem = new IndividualItem(itemName,itemUnit,itemReductionStrategy);
				ConcessionItemStorage.addItem(newItem);
				ItemPriceStorage.addPrice(itemName, sizePrices);
				break;
			case 2:
				String comboName = scan.nextLine();
				ArrayList<ConcessionItem> selectedItems = new ArrayList<>();
				while(true) {
					//TODO: REPLACE THIS WITH VIEWITEMCOMMAND MOVE THIS ENTIRE THING INTO ITS OWN COMMAND
					selectedItems.add(ConcessionItemFactory.selectConcessionItemByIndex(Integer.parseInt(scan.nextLine())-1));
					if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
						int userInput = 0;
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
					System.out.println("Do you want to add another item to the combo? Y/N");
					String menuInput = scan.nextLine().trim();
					if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
						break;
					}
				}
				ConcessionItem[] arrayItems = selectedItems.toArray(new ConcessionItem[0]);
				Combo newCombo = new Combo(comboName, arrayItems);
				Map<SizeType,Double> comboPrice = new HashMap<>();
				System.out.println("Input price.");
				comboPrice.put(SizeType.NONE, Double.parseDouble(scan.nextLine()));
				ConcessionItemStorage.addItem(newCombo);
				ItemPriceStorage.addPrice(comboName, comboPrice);
				break;
			default:
			}
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
