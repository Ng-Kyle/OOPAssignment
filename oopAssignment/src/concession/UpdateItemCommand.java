package concession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.Command;
import main.CommandInvoker;
import customerMain.SelectConcessionItemsCommand;


public class UpdateItemCommand implements Command<Void> {
	private Scanner scan;
	private CommandInvoker menuInvoker;
	
	public UpdateItemCommand(Scanner scan, CommandInvoker menuInvoker) {
		this.scan = scan;
		this.menuInvoker = menuInvoker;
	}
	
	public Void execute() {
		ArrayList<ConcessionItem> storedItems = ConcessionItemStorage.getAllItems();
		while(true) {
			System.out.println("\n-------------------------UPDATE CONCESSION------------------------");
			menuInvoker.executeCommand(new ViewItemCommand());
			System.out.println("Select a concession item or 0 to cancel.");
			int userInput;
			try {
				userInput = Integer.parseInt(scan.nextLine());
				if(userInput == 0) {
					break;				
				}
				if (userInput < 1 || userInput > storedItems.size()) {
					throw new Exception();
				}
			}catch (Exception e) {
				System.out.println("Invalid Selection! Please enter a number from the list");
				continue;
			}
			ConcessionItem chosenItem = storedItems.get(userInput - 1);
			
			while(true) {
				String originalName = chosenItem.getName();
				System.out.println("\n** Updating: " + chosenItem.getName() + " **");
				System.out.println("1. Change Name");
				
				if(chosenItem instanceof IndividualItem) {
					System.out.println("2. Update Prices (Medium & Large)");
				} else if(chosenItem instanceof Combo) {
					System.out.println("2. Re-select Combo Items");
					System.out.println("3. Update Combo Price");
				}
				System.out.println("0. Save and Return");
				System.out.println("Select an option.");
				try {
					int updateChoice = Integer.parseInt(scan.nextLine());
					if(updateChoice == 0) {
						ConcessionItemStorage.saveItem();
						System.out.println("Changes Saved Successfully");
						break;
					}
					if(chosenItem instanceof IndividualItem) {
						switch(updateChoice) {
						case 1:
							System.out.println("Enter New Name: ");
							String newName = scan.nextLine();
							chosenItem.setName(newName);
							if(!originalName.equals(chosenItem.getName())) {
								ConcessionItemStorage.updateKey(originalName, chosenItem.getName());
								ItemPriceStorage.updateKey(originalName, chosenItem.getName());
							}
							for (ConcessionItem item : storedItems) {
							    updateComboItems(item,originalName,newName);
							}
							ConcessionItemStorage.saveItem();
							System.out.println("Saved");
							break;
						case 2:
							Map<SizeType,Double> sizePrices = new HashMap<>();
							System.out.println("Enter New MEDIUM Price: RM");
							sizePrices.put(SizeType.MEDIUM, Double.parseDouble(scan.nextLine()));
							System.out.println("Enter New LARGE Price: RM");
							sizePrices.put(SizeType.LARGE, Double.parseDouble(scan.nextLine()));
							ItemPriceStorage.updatePrice(chosenItem.getName(), sizePrices);
							ConcessionItemStorage.saveItem();
							System.out.println("Saved");
							break;
						default:
						}
					}
					if(chosenItem instanceof Combo) {
						switch(updateChoice) {
						case 1:
							System.out.println("Enter new name");
							String newName = scan.nextLine();
							chosenItem.setName(newName);
							if(!originalName.equals(chosenItem.getName())) {
								ItemPriceStorage.updateKey(originalName, chosenItem.getName());
							}
							ConcessionItemStorage.saveItem();
							System.out.println("Saved");
							break;
						case 2:
							ArrayList<ConcessionItem> selectedItems = new ArrayList<>();
							selectedItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan,menuInvoker));
							if (selectedItems != null) {	
							ConcessionItem[] comboItems = selectedItems.toArray(new ConcessionItem[0]);
							((Combo) chosenItem).setComboItems(comboItems);
							ConcessionItemStorage.saveItem();
							System.out.println("Saved");
							}
							break;
						case 3:
							Map<SizeType,Double> comboPrice = new HashMap<>();
							System.out.println("Enter new COMBO Price: RM");
							comboPrice.put(SizeType.NONE, Double.parseDouble(scan.nextLine()));
							ItemPriceStorage.updatePrice(chosenItem.getName(), comboPrice);
							ConcessionItemStorage.saveItem();
							System.out.println("Saved");
							break;
						}
					}	
				}catch (Exception e) {
					System.out.println("Invalid Selection! Please check your values.");
				}
			}
		}
		return null;
	}
	private void updateComboItems(ConcessionItem item, String oldName, String newName) {
	    if (item instanceof Combo) {
	        Combo combo = (Combo) item;
	        ConcessionItem[] comboItems = combo.getComboItems();

	        for (ConcessionItem ci : comboItems) {
	            if (ci.getName().equals(oldName)) {
	                ci.setName(newName);
	            }
	            updateComboItems(ci, oldName, newName);
	        }
	    }
	}
}


