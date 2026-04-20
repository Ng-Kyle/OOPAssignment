package concession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import customerMain.SelectConcessionItemsCommand;
import main.Command;
import main.CommandInvoker;


public class AddItemCommand implements Command<Void> {
	private Scanner scan;
	private CommandInvoker menuInvoker;
	public AddItemCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
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
				ArrayList<ConcessionItem> selectedItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan,menuInvoker));
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
