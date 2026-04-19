package concession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class AddItemCommand implements Command<Void> {
	private Scanner scan;
	public AddItemCommand(Scanner scan) {
		this.scan = scan;
	}
	public Void execute() {
		while(true) {
			ArrayList<ConcessionItem> availableItems = ConcessionItemStorage.getAllItems();
			Map<String,Map<SizeType,Double>> itemPrices = ItemPriceStorage.getItemPrices();
			int itemType = Integer.parseInt(scan.nextLine());
			switch(itemType) {
			case 1:
				String itemName = scan.nextLine();
				UnitType itemUnit = UnitType.valueOf(scan.nextLine());
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
				itemPrices.put(itemName, sizePrices);
				IndividualItem newItem = new IndividualItem(itemName,itemUnit,itemReductionStrategy);
				ConcessionItemStorage.addItem(newItem);
				CSVLoader.saveCSV(availableItems, FilePath.CONCESSION_ITEMS.toString());
				break;
			case 2:
				String comboName = scan.nextLine();
				
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
