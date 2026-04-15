package concession;
/*A combo that contains multiple concession items
Example: Family Combo that includes 2 Popcorn boxes and 4 Drinks
*/

public class Combo extends ConcessionItem {
    private ConcessionItem[] comboItems;
    private static ComboReductionStrategy strategy = new ComboReductionStrategy();

    public Combo(String name,ConcessionItem[] comboItems) {
        super(name);
        this.comboItems = comboItems;
        super.setSize(SizeType.NONE);
        
    }
    
    

    public ConcessionItem[] getComboItems() {
        return comboItems;
    }

    public void setComboItems(ConcessionItem[] comboItems) {
        this.comboItems = comboItems;
    }
    

    public void reduceStock() {
    	strategy.reduceStock(this);
    }
    @Override
    public String toString() {
        return String.format("%s (Combo)", getName());
    }
    public String toCSV() {
    	String csvLine = super.getName() + ",COMBO,";
    	for(int i = 0; i<comboItems.length; i++) {
    		csvLine += comboItems[i].getName() + ":";
    		csvLine += comboItems[i].getSize();
    		if(i<comboItems.length-1) {
    			csvLine+=";";
    		}
    	}
    	csvLine += "," + strategy.getClass().getSimpleName();
    	return csvLine;
    }
    public static Combo fromCSV(String content) {
    	String[] values = content.split(",");
    	String[] itemValues = values[2].split(";");
    	ConcessionItem[] comboItems = new ConcessionItem[itemValues.length];
    	for(int i = 0; i < itemValues.length; i++) {
    		String[] itemSizeValues = itemValues[i].split(":");
    		comboItems[i] = ConcessionItemFactory.selectConcessionItemByName(itemSizeValues[0]);
    		comboItems[i].setSize(SizeType.valueOf(itemSizeValues[1]));
    	}
    	
    	return new Combo(values[0],comboItems);
    }
}