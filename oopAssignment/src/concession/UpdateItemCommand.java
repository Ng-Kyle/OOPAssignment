package concession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.Command;


public class UpdateItemCommand implements Command<Void> {
	private Scanner scan;
	
	public UpdateItemCommand(Scanner scan) {
		this.scan = scan;
	}
	
	public Void execute() {
		ArrayList<ConcessionItem> storedItems = ConcessionItemStorage.getAllItems();
		while(true) {
			System.out.println("Select a concession item.");
			int userInput = Integer.parseInt(scan.nextLine());
			if(userInput == 0) {
				break;				
			}
			while(true) {
				ConcessionItem chosenItem = storedItems.get(userInput - 1);
				String originalName = chosenItem.getName();
				System.out.println("Select an option.");
				int updateChoice = Integer.parseInt(scan.nextLine());
				if(updateChoice == 0) {
					break;
				}
				if(chosenItem instanceof IndividualItem) {
					switch(updateChoice) {
					case 1:
						String newName = scan.nextLine();
						chosenItem.setName(newName);
						if(!originalName.equals(chosenItem.getName())) {
							ConcessionItemStorage.updateKey(originalName, chosenItem.getName());
							ItemPriceStorage.updateKey(originalName, chosenItem.getName());
						}
						ConcessionItemStorage.saveItem();
						System.out.println("Saved");
						break;
					case 2:
						Map<SizeType,Double> sizePrices = new HashMap<>();
						sizePrices.put(SizeType.MEDIUM, Double.parseDouble(scan.nextLine()));
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
						while(true) {
							//TODO: REPLACE THIS WITH VIEWITEMCOMMAND MOVE THIS ENTIRE THING INTO ITS OWN COMMAND
							selectedItems.add(ConcessionItemFactory.selectConcessionItemByIndex(Integer.parseInt(scan.nextLine())-1));
							if(selectedItems.get(selectedItems.size()-1) instanceof IndividualItem) {
								int sizeChoice = 0;
								sizeChoice = Integer.parseInt(scan.nextLine());
								switch(sizeChoice) {
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
						ConcessionItem[] comboItems = selectedItems.toArray(new ConcessionItem[0]);
						((Combo) chosenItem).setComboItems(comboItems);
						ConcessionItemStorage.saveItem();
						System.out.println("Saved");
						break;
					case 3:
						Map<SizeType,Double> comboPrice = new HashMap<>();
						comboPrice.put(SizeType.NONE, Double.parseDouble(scan.nextLine()));
						ItemPriceStorage.updatePrice(chosenItem.getName(), comboPrice);
						ConcessionItemStorage.saveItem();
						System.out.println("Saved");
						break;
					}
						
				}
			}
		}
		return null;
	}
}
