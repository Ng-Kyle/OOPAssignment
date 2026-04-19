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
}