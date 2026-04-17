package concession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.FileIO;
public class ItemPriceStorage {
	private static Map<String, Map<SizeType,Double>> itemPrices = new HashMap<>();
	
	static {
		ArrayList<String> csvContent = FileIO.readFile("storedData/itemPrice.csv");
		for(int i =0; i<csvContent.size();i++) {
			String[] values = csvContent.get(i).split(",");
			itemPrices.put(values[0], new HashMap<>());
			itemPrices.get(values[0]).put(SizeType.valueOf(values[1]), Double.parseDouble(values[2]));
		}
	}
	public static Map<String,Map<SizeType,Double>> getItemPrices(){
		return itemPrices;
	}
	
	public String toCSV() {
		String csvLine = "";
		for(Map.Entry<String,Map<SizeType,Double>> itemNameEntry: itemPrices.entrySet()) {
			String itemName = itemNameEntry.getKey();
			Map<SizeType,Double> itemPriceMap = itemNameEntry.getValue();
			for(Map.Entry<SizeType,Double> itemPriceEntry: itemPriceMap.entrySet()){
				csvLine += itemName+","+itemPriceEntry.getKey()+ "," + itemPriceEntry.getValue() +"\n";
			}
		}
		return csvLine;
	}
	
}
