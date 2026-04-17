package concession;

public class ConcessionItemFactory {
	public static ConcessionItem selectConcessionItemByIndex(int index) {
		ConcessionItem userItem = ConcessionItemStorage.getItemByIndex(index);
		return copyItem(userItem);
	}
	public static ConcessionItem selectConcessionItemByName(String name) {
		ConcessionItem userItem = ConcessionItemStorage.getItemByName(name);
		return copyItem(userItem);
	}
	
	public static ConcessionItem copyItem(ConcessionItem userItem) {
		if(userItem instanceof IndividualItem) {
			IndividualItem userIndividualItem = (IndividualItem)userItem;
			IndividualItem copyItem = new IndividualItem(userIndividualItem.getName(), userIndividualItem.getStockReductionStrategy());
			copyItem.setSize(userIndividualItem.getSize());
			return copyItem;
		}
		if(userItem instanceof Combo) {
			Combo userComboItem = (Combo)userItem;
			ConcessionItem[] originalItems = userComboItem.getComboItems();
			ConcessionItem[] copiedItems = new ConcessionItem[originalItems.length];
			for(int i=0; i<copiedItems.length;i++) {
				copiedItems[i] = copyItem(originalItems[i]);
			}
			return new Combo(userComboItem.getName(),copiedItems);
		}
		return null;
	}
}
