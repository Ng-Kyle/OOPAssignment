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
        while (true) {
            try {
                System.out.println("\n=============================================================");
                System.out.println("                            ITEMS                            ");
                System.out.println("=============================================================");
                System.out.println("1. Individual Items");
                System.out.println("2. Combo Items");
                System.out.println("0. Exit");
                System.out.println("-------------------------------------------------------------");

                int itemType = Integer.parseInt(scan.nextLine());
                if (itemType == 0) break;

                switch (itemType) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String itemName = scan.nextLine();

                        // FIX: Use a choice variable for the input, then assign to a UnitType variable
                        System.out.print("Pick item type: 1.ML 2.Unit: ");
                        int unitChoice = Integer.parseInt(scan.nextLine());
                        UnitType itemUnit = null; 
                        
                        switch (unitChoice) {
                            case 1:
                                itemUnit = UnitType.ML;
                                break;
                            case 2:
                                itemUnit = UnitType.UNIT;
                                break;
                            default:
                            	System.out.println("Invalid choice! Please enter the correct number.");
                                return null;
                        }

                        System.out.println("Pick your stock reduction strategy.");
                        System.out.println("1. Popcorn Reduction Strategy.");
                        System.out.println("2. Drink Reduction Strategy.");
                        System.out.println("3. Popcorn Reduction Strategy.");
                        int strategyChoice = Integer.parseInt(scan.nextLine());
                        StockReductionStrategy itemReductionStrategy = null;

                        switch (strategyChoice) {
                            case 1:
                                itemReductionStrategy = new PopcornReductionStrategy();
                                break;
                            case 2:
                                itemReductionStrategy = new DrinkReductionStrategy();
                                break;
                            default:
                                itemReductionStrategy = new PopcornReductionStrategy();
                        }
                        
                        Map<SizeType, Double> sizePrices = new HashMap<>();
                        System.out.print("Enter Medium Price: ");
                        sizePrices.put(SizeType.MEDIUM, Double.parseDouble(scan.nextLine()));
                        System.out.print("Enter Large Price: ");
                        sizePrices.put(SizeType.LARGE, Double.parseDouble(scan.nextLine()));

                        IndividualItem newItem = new IndividualItem(itemName, itemUnit, itemReductionStrategy);
                        ConcessionItemStorage.addItem(newItem);
                        ItemPriceStorage.addPrice(itemName, sizePrices);
                        System.out.println("Individual item added.");
                        break;

                    case 2:
                        System.out.print("Enter combo name: ");
                        String comboName = scan.nextLine();
                        ArrayList<ConcessionItem> selectedItems = menuInvoker.executeCommand(new SelectConcessionItemsCommand(scan, menuInvoker));
                        
                        if (selectedItems.size() == 0) {
                        	throw new NoItemSelectedException();
                        }
                        ConcessionItem[] arrayItems = selectedItems.toArray(new ConcessionItem[0]);
                        Combo newCombo = new Combo(comboName, arrayItems);
                        
                        Map<SizeType, Double> comboPrice = new HashMap<>();
                        System.out.print("Input combo price: ");
                        comboPrice.put(SizeType.NONE, Double.parseDouble(scan.nextLine()));
                        
                        ConcessionItemStorage.addItem(newCombo);
                        ItemPriceStorage.addPrice(comboName, comboPrice);
                        System.out.println("Combo added.");
                    
                        break;
                }
            }
            catch (NoItemSelectedException nise) {
            	System.out.println("You didn't select any items for your combo!");
            }
            catch (Exception e) {
                System.out.println("Invalid input. Please enter valid numbers and prices.");
            }

            System.out.print("Do you want to add another item? (Y/N): ");
            String menuInput = scan.nextLine().trim();
            if (menuInput.isEmpty() || Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
                break;
            }
        }
        return null;
    }
}
