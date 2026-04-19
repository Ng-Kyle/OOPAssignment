package concession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.CSVLoader;
import main.FileIO;
import main.FilePath;

public class ConcessionItemStorage {
	private static ArrayList<ConcessionItem> availableItems = new ArrayList<>();
	private static Map<String,Double> stockQuantity = new HashMap<>();
	static {
		ArrayList<String> csvContent = FileIO.readFile("storedData/availableItems.csv");
		for(int i = 0; i < csvContent.size(); i++) {
			String[] values = csvContent.get(i).split(",");
			if(values[1].equals("INDIVIDUAL")) {
				availableItems.add(IndividualItem.fromCSV(csvContent.get(i)));
			}
		}
		for(int i =0; i< csvContent.size(); i++) {
			String[] values = csvContent.get(i).split(",");
			if(values[1].equals("COMBO")) {
				availableItems.add(Combo.fromCSV(csvContent.get(i)));
			}
		}
		
		ArrayList<String> csvStockContent = FileIO.readFile("storedData/stockQuantity.csv");
		for(int i =0; i<csvStockContent.size();i++) {
			String[] values = csvStockContent.get(i).split(",");
			stockQuantity.put(values[0],Double.parseDouble(values[1]));
		}
	}
	public static void availableItemsToCSV() {
		String csvLine = "";
		for(int i = 0; i < availableItems.size(); i++) {
			csvLine += availableItems.get(i).toCSV() + "\n";
		}
		FileIO.writeFile(csvLine, FilePath.CONCESSION_ITEMS.toString());
	}
	
	public static void updateKey(String oldName, String newName) {
		double oldQuantity = stockQuantity.remove(oldName);
		stockQuantity.put(newName, oldQuantity);
		stockQuantityToCSV();
	}
	public static void stockQuantityToCSV() { 
		String csvLine =""; 
		for(Map.Entry<String,Double> entry: stockQuantity.entrySet()) {
			csvLine += entry.getKey()+","+entry.getValue()+"\n";
		}
		FileIO.writeFile(csvLine, FilePath.STOCK.toString());;
	}

	
	public static ArrayList<ConcessionItem> getAllItems() {
		return availableItems;
	}
	
	public static ConcessionItem getItemByIndex(int index) {
		return availableItems.get(index);
	}
	public static ConcessionItem getItemByName(String name) {
		for(int i = 0; i<availableItems.size(); i++) {
			if(availableItems.get(i).getName().equals(name)) {
				return availableItems.get(i);
			}
		}
		return null;
	}
	public static Map<String,Double> getStockQuantity(){
		return stockQuantity;
	}
	public static void removeStock(String name) {
		stockQuantity.remove(name);
	}
	public static void addItem(ConcessionItem item) {
		availableItems.add(item);
		if(!stockQuantity.containsKey(item.getName()) && item instanceof IndividualItem) {
			stockQuantity.put(item.getName(), 0.d); 
		}
		ConcessionItemStorage.stockQuantityToCSV();
		CSVLoader.saveCSV(availableItems, FilePath.CONCESSION_ITEMS.toString());
	}
	
	public static void removeItem(int itemChoice) {
		String itemName = availableItems.get(itemChoice-1).getName();
		if(availableItems.get(itemChoice - 1) instanceof IndividualItem) {
			ConcessionItemStorage.removeStock(itemName);
		}
		ItemPriceStorage.removePrice(itemName);
		availableItems.remove(itemChoice-1);
		saveItem();
	}
	
	public static void saveItem() {
		ConcessionItemStorage.availableItemsToCSV();
		ConcessionItemStorage.stockQuantityToCSV();
		ItemPriceStorage.toCSV();
	}
	public static void addStock(String chosenItemName, double amount) {
		double currentStock = stockQuantity.get(chosenItemName);
		stockQuantity.replace(chosenItemName, currentStock + amount);
		stockQuantityToCSV();
	}
	public static void reduceStock(String itemName, double amount) {
		stockQuantity.put(itemName, (stockQuantity.get(itemName)-amount));
		stockQuantityToCSV();
		
	}
	
}
