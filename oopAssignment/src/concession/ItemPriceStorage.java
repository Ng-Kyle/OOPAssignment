package concession;
import java.util.HashMap;
import java.util.Map;
public class ItemPriceStorage {
	private static Map<String, Map<SizeType,Double>> itemPrices = new HashMap<>();
	
	static {
		itemPrices.put("Popcorn", new HashMap<>());
		itemPrices.get("Popcorn").put(SizeType.MEDIUM,20.00);
	}
	public static Map<String,Map<SizeType,Double>> getItemPrices(){
		return itemPrices;
	}
}
