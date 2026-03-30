package concession;

public class ComboReductionStrategy implements StockReductionStrategy {

	@Override
	public void reduceStock(ConcessionItem item) {
		if(item instanceof IndividualItem) {
			item.reduceStock();
		}
		else if(item instanceof Combo) {
			Combo comboItem = (Combo)item;
			ConcessionItem[] comboIndividualItems = comboItem.getComboItems();
			for(int i =0; i<comboIndividualItems.length; i++) {
				reduceStock(comboIndividualItems[i]);
				
			}
		}
		
	}
}
