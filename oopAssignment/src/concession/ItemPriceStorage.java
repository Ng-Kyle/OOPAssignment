package concession;
import java.util.HashMap;
import java.util.Map;
public class ItemPriceStorage {
	private static Map<String, Map<SizeType,Double>> itemPrices = new HashMap<>();
	
	static {
		itemPrices.put("Popcorn", new HashMap<>());
		itemPrices.put("Popcorn Combo", new HashMap<>());
<<<<<<< HEAD


		itemPrices.put("Coke", new HashMap<>());


		itemPrices.put("Coke", new HashMap<>());

=======
		itemPrices.put("Coke", new HashMap<>());
>>>>>>> branch 'Sub_Classes' of git@github.com:Ng-Kyle/OOPAssignment.git
		itemPrices.get("Popcorn").put(SizeType.MEDIUM,20.00);
<<<<<<< HEAD


		itemPrices.get("Coke").put(SizeType.MEDIUM, 7.00);


		itemPrices.get("Coke").put(SizeType.MEDIUM, 7.00);

=======
		itemPrices.get("Coke").put(SizeType.MEDIUM, 7.00);
>>>>>>> branch 'Sub_Classes' of git@github.com:Ng-Kyle/OOPAssignment.git
		itemPrices.get("Popcorn Combo").put(SizeType.NONE,30.00);
	}
	public static Map<String,Map<SizeType,Double>> getItemPrices(){
		return itemPrices;
	}
}
