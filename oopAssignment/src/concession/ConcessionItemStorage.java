package concession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.FileIO;

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
	public static String availableItemsToCSV() {
		String csvLine = "";
		for(int i = 0; i < availableItems.size(); i++) {
			csvLine += availableItems.get(i).toCSV() + "\n";
		}
		return csvLine;
	}
	
	public static String stockQuantityToCSV() { 
		String csvLine =""; 
		for(Map.Entry<String,Double> entry: stockQuantity.entrySet()) {
			csvLine += entry.getKey()+","+entry.getValue()+"\n";
		}
		return csvLine;
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
	public static void addItem(ConcessionItem item) {
		availableItems.add(item);
		if(!stockQuantity.containsKey(item.getName())) {
			stockQuantity.put(item.getName(), 0.d); 
		}
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
