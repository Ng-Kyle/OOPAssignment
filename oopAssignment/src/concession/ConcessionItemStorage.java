package concession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcessionItemStorage {
	private static ArrayList<ConcessionItem> availableItems = new ArrayList<>();
	private static Map<String,Double> stockQuantity = new HashMap<>();
	static {
		availableItems.add(new IndividualItem("Popcorn",20.00,SizeType.MEDIUM,UnitType.ML,new PopcornReductionStrategy()));
		for(int i =0; i<availableItems.size();i++) {
			stockQuantity.put(availableItems.get(i).getName(),200.00);
		}
	}
	
	
	public static ArrayList<ConcessionItem> getAllItems() {
		return availableItems;
	}
	
	public static ConcessionItem getItem(int index) {
		return availableItems.get(index);
	}
	
	public static void addItem(ConcessionItem item) {
		availableItems.add(item);
	}
	public static void reduceStock(String itemName, double amount) {
		stockQuantity.put(itemName, (stockQuantity.get(itemName)-amount));
		
	}
	
}
