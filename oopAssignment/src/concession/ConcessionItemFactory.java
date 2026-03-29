package concession;

public class ConcessionItemFactory {
	public static ConcessionItem selectConcessionItem(int index) {
		ConcessionItem userItem = ConcessionItemStorage.getItem(index);
		return copyItem(userItem);
	}

	
	public static ConcessionItem copyItem(ConcessionItem userItem) {
		if(userItem instanceof IndividualItem) {
			IndividualItem userIndividualItem = (IndividualItem)userItem;
			return new IndividualItem(userIndividualItem.getName(),userIndividualItem.getPrice(),userIndividualItem.getSize(),
					userIndividualItem.getUnit(),userIndividualItem.getStrategy());
		}
		if(userItem instanceof Combo) {
			Combo userComboItem = (Combo)userItem;
			ConcessionItem[] originalItems = userComboItem.getComboItems();
			ConcessionItem[] copiedItems = new ConcessionItem[originalItems.length];
			for(int i=0; i<copiedItems.length;i++) {
				copiedItems[i] = copyItem(originalItems[i]);
			}
			return new Combo(userComboItem.getName(),userComboItem.getPrice(),copiedItems);
		}
		return null;
	}
}
